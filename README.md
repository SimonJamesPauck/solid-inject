![SOLID Inject](logo.svg)

# A Dependency Injector for SOLID Design

## Principles & Goals

The design and features of this library should support the following

- Encourage the use of the _dependency injection pattern_ and other SOLID principles.
- Make code simpler to understand and work with.
- Make it easy to visualize and debug the dependency graph.

These relative goals should be assessed against the benchmark of manual dependency management.

The main identified shortcomings of manual dependency management are

- A bulky composition root.
- Boilerplate for decoupling construction of new instances other than at the composition root.
- Instantiation scoping. i.e. Reusing instances across certain parts of a dependency tree.

## Getting Started

This library is currently published as a maven artifact through [Jitpack.io](https://jitpack.io/#spauck/solid-inject).
The recommended tag is `master-SNAPSHOT`.
Only the `core` module is required.

## Basic Usage

Usage is documented through the [api tests](apitests/src/test/kotlin/solid/inject/).

For most usage it should not be necessary to fill constructor parameters manually.

## Features

### Injector Forking

Forking an injector produces an new injector (child) with the same context as the one from which it was forked (parent).
The child will have the same bindings, registrations and scopings as the parent, however, they are distinct.
The child can be modified without affecting the parent.
The parent can be modified without affecting the child.
They will, however, still be bound through any state present in their registrations unless those types are re-registered.
This feature is the basis for scoping.

### Instance Scoping

Scoping allows one type's instance creation to be constrained within the dependency tree of another type.
This is commonly required and used for singleton (application scoped) or web-request scoped instances, among others.

## Development

### Project Modules

- Core: contains the production code.
- Apitests: is purely for testing the public API.
  This is a distinct module so it can depend on core in the same way any other project would.
  This module also provides the bulk of the documentation by way of code examples.
- Generated: is a small app used to generate the InjectionExtensions.
  It has yet to be automatically incorporated into the build.

## Credits

The following resources were used for the logo.

- [Material Design Colours](https://material.io/resources/color/)
- [Josefin Sans Font](https://fonts.google.com/specimen/Josefin+Sans)
