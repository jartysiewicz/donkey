/*
 * Copyright 2020 AppsFlyer
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.appsflyer.donkey.client.ring;

import clojure.lang.IPersistentMap;
import clojure.lang.Keyword;
import com.appsflyer.donkey.ValueExtractor;
import com.appsflyer.donkey.util.TypeConverter;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import org.jetbrains.annotations.Nullable;

import static com.appsflyer.donkey.util.TypeConverter.toPersistentMap;

/**
 * The Enum class encapsulates the logic of translating between an {@link HttpResponse}
 * and a Ring response.
 * Each element corresponds to a Ring response field. It implements getting the field's
 * name as a {@link Keyword}, and extracting the corresponding value from
 * the {@link HttpResponse}.
 */
public enum RingResponseField implements ValueExtractor<HttpResponse<Buffer>> {
  
  STATUS("status") {
    @Override
    public Integer from(HttpResponse<Buffer> res) {
      return res.statusCode();
    }
  },
  HEADERS("headers") {
    @Nullable
    @Override
    public IPersistentMap from(HttpResponse<Buffer> res) {
      MultiMap headers = res.headers();
      if (headers.isEmpty()) {
        return null;
      }
      return toPersistentMap(headers, TypeConverter::stringJoiner);
    }
  },
  BODY("body") {
    @Override
    public byte[] from(HttpResponse<Buffer> res) {
      Buffer body = res.body();
      if (body != null) {
        return body.getBytes();
      }
      return BYTES;
    }
  };
  
  private static final byte[] BYTES = new byte[0];
  private final Keyword keyword;
  
  RingResponseField(String field) {
    keyword = Keyword.intern(field);
  }
  
  /**
   * @return The field name as a Keyword
   */
  public Keyword keyword() {
    return keyword;
  }
}
