## v0.4.1

- Moved from Travis CI to GitHub Actions

## v0.4.0

- Debug mode is now set when creating a `Donkey` instance rather than creating
  a `DonkeyServer` or `DonkeyClient`. This fixes issues where enabling debug
  mode on the server / client and not on the other would sometimes disable debug
  mode for both.
- Turned on Clojure spec assertions
- Global exception handler - In debug mode, a stack trace is added to the
  exception if it doesn't include one.
- Fixed release script not pushing changes to GitHub.

## v0.3.0

- Added support for user-defined error handlers when creating a server. The
  server options support a new field - `:error-handlers`. Users can supply a map
  of http-status-code -> handler-function.

## v0.2.0

- `version-bump.sh` script was removed in favor of:
    - `version-change.sh` Update the project to a new version.
    - `dev-branch.sh` Create a new development branch according to an input
      version.
    - `release.sh` Used to remove `-SNAPSHOT` suffix from project version, add a
      release tag, and deploy to clojars.org.
- Updated the `README.md` documentation.
- Added GitHub issue templates.
- Added TCP configuration options to `com.appsflyer.donkey.core/create-server`:
    - `tcp-no-delay`
    - `tcp-quick-ack`
    - `tcp-fast-open`
    - `socket-linger-seconds`
    - `accept-backlog`
- Change default client connection idle timeout from 60 to 30 seconds.
- Dependency updates

| Dependency | Old Version | New Version |
| ------- | -----: | -----: |
| vertx | 3.9.4 | 4.0.0 |
| junit | 5.6.2 | 5.7.0 |
| jsonista | 0.2.7 | 0.3.0 |
| mockito | 3.4.6 | 3.6.28 |
| jetbrains/annotations | 13.0 | 20.1.0 |
| ring-core | 1.8.1 | 1.8.2 |
