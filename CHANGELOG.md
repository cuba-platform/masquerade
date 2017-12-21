# Changelog

All notable changes to this project will be documented in this file.

## Unreleased

## 1.0.0 - 2017-12-22

### Added

- Component interface
- Standard UI library, including: fields, containers, table, dialog, notifications
- Components factory class that instantiates UI Component instances
- Composite class - convenient parent class for composite UI components: panels, screens, tabs, etc.
- Untyped class - convenient class for non-implemented-yet UI components
- @Wire annotation for field of Composite class for DI injection of nested components
- @Log annotation that is used for automatic logging of method calls of UI components
- Selectors utility class that includes useful Selenide selectors: byCubaId, byPath, byChain
- Conditions utility class that includes useful Conditions for UI components: ENABLED, EDITABLE, REQUIRED, etc
- ComponentConfig interface for custom component sets that can be imported in projects
- DefaultComponentConfig class that is imported by default before all ComponentConfig implementations registered 
  using Java SPI