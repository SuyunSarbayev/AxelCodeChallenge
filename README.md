# AxelCodeChallenge

MVVM was chosen as a design pattern for this task with multi module support

Compose is ui standard tool, it is more reusable and can be expanded to full test driven design
system

Hilt as a standard tool for dependency injection, easy to use and suggested by google

Multi-module
Multi module architecture was chosen with the aim to optimize the build times, separate the and
encapsulate
the functionality of each module

Each module got public api and private impl module:

public api must expose sharable data, in case if other modules need to access it (repository,
navigation, data classes)
private api must contain domain logic that must remain hidden from outside of the scope of the
module
Network
Repository pattern is chosen to provide async access to remote data source, flows as a standard tool
to provide observer like behavior for processing responses

Testing
JUnit, turbine is chosen to support tests, ViewModels success and failure states are covered for
further suggestions

Snapshot tests must be added to support UI testing, each compose widget can be isolated and added
to design module which can be used to display different variations of widget

Instrumented tests can be implemented as a CI/CD step but not mandatory to prevent it being a
blocker
