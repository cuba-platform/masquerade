# Changelog

All notable changes to this project will be documented in this file.

## Unreleased

## 1.0.4 - 2018-01-23

### Fixed

- AppMenu openItem in Chrome

## 1.0.3 - 2018-01-19

### Fixed

- CheckBox READONLY / EDITABLE conditions support

## 1.0.2 - 2018-01-16

### Fixed

- Table.getRows() with Selectors.isVisible()

### Added

- Table.getCell() with Selectors.byRowColIndexes(r, c)

## 1.0.1 - 2018-01-09

### Added

#### web

- Table.getRows() with Selectors.isVisible()
- GroupBox condition support for captionContains(..)
- Updated Selenium / Selenide version to 3.8.1 / 4.9.1

## 1.0.0 - 2017-12-28

### Added

#### web

- Component interface
- Standard UI library, including: fields, containers, table, dialog, notifications
- Components factory class that creates Component instances
- Composite class - convenient parent class for composite UI components: panels, screens, tabs, etc.
- Untyped class - convenient class for non-implemented-yet UI components
- @Wire annotation for fields of Composite class for DI injection of nested components
- @Log annotation that is used for automatic logging of method calls of UI components
- Selectors utility class that includes useful Selenide selectors: byCubaId, byPath, byChain
- Conditions utility class that includes useful Conditions for UI components: ENABLED, EDITABLE, REQUIRED, etc
- ComponentConfig interface for custom component sets that can be imported in projects
- DefaultComponentConfig class that is imported by default before all ComponentConfig implementations registered 
  using Java SPI
  
#### connector

- Connectors factory class that creates different connector-like objects
- JMX connector implementation and Connectors.jmx(...) based on standard Java JMX subsystem
- @JmxName annotation for JMX object interfaces 
- REST-API v2 connector implementation and Connectors.restApi(...) based on Retrofit 2 
- Built-in authentication service for REST-API v2