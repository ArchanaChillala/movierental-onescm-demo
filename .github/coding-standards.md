## CODING GUIDELINES (MANDATORY)

### Goal
Ensure code is *extremely readable*, composed of *very small and focused* methods and classes, and **avoids all code smells**.

### General Principles
- Code is for **humans first**, computers second
- **Express intent clearly** - if it's not obvious, rewrite it. Variables, method names, Class names to be self-explanatory
- Prefer **self-documenting code**; comments as the last resort
- **Small is beautiful** - Prefer **small**, focused methods and classes.
- **Duplication is a bad sign** - extract and reuse
- **Keep it Simple Stupid (KISS)** - simpler is always better, reduce complexity as much as possible
- **You ain't gonna need it (YAGNI)** - Avoid over-engineering
- **Boy Scout Rule (BSR)** - leave the campground cleaner than you found it
- **Tell, Don't Ask** - to promote loose coupling between objects
- **Be Consistent** - if you do something a certain way, do all similar things in the same way. Follow conventions that you see in the codebase
- **Encapsulate Boundary Conditions** - boundary conditions are hard to keep track of, put the processing for them in one place
- **Avoid logical dependency** - don't write methods which work correctly depending on something else in the same class
- Avoid negative conditionals
- Keep configurable data at high levels
- Separate multithreading code; document thread-safety assumptions
- Prevent over-configurability
- Use dependency injection
- Externalise messages, regex patterns and constants
- Standardize special characters

### SOLID Design Principles
- **SRP (Single Responsibility Principle)** - Each method/class does one thing only
- **OCP (Open/Close Principle)** - Open for extension, closed for modification
- **LSP (Liskov Substitution Rule)** - Subtypes substitutable for base types
- **ISP (Interface Segregation Principle)** - Small, focused interfaces
- **DIP (Dependency Inversion Principle)** - Depend on abstractions, not concretions

### Code Smells to Identify and remove
- Long Method
- Large Class
- Primitive Obsession
- Long Parameters List: **maximum**: 3
- Data Clumps
- Alternative classes with Different Interfaces
- Switch Statements
- Temporary Field
- Refused Bequest
- Divergent Change
- Parallel Inheritance Hierarchies
- Shotgun Surgery
- Comments
- Data Class
- Lazy Class
- Duplicated code
- Dead Code
- Speculative Generality
- Feature Envy
- Incomplete Library Class
- Middle Man
- Inappropriate Intimacy
- Message Chains
- Magic numbers (also magic literals): Replace magic numbers with named constants

### Class Design
- **Should be small**: ~100 lines max. If exceeded, split into smaller classes
    - Small number of fields: maximum: 7, split where needed
    - Small number of public methods: maximum: ~3
- **Single clear purpose**, aligned with **Domain-Driven Design (DDD)**
- **Encapsulation** - Hide internal structure.
- **Domain-focused naming** (e.g., `PolicyRenewalService`, not `HelperUtil`)
    - Avoid vague classes like `Manager`, `Processor`, `Handler` unless domain-specific
    - Use **domain/ubiquitous language** for classes, methods, and variables
- **Avoid "God objects"** each class has a focused role
- Base class should know nothing about their derivatives.
- Prefer dedicated value objects to a primitive type.
    - Keep DTOs dumb, mapping centralised.
    - Logic belongs in services or domain entities carefully.
    - Centralize mapping in Mapper classes (e.g., `TemplateMapper`)
    - Consider entity instance methods for self-contained transformations that do not depend on external DTOs.
- Prefer non-static methods to static methods.
- **Prefer immutability** where practical
- Prefer polymorphism to if/else or switch/case
- Favour **Composition over inheritance** - prefer small collaborating components
- Follow **Law of Demeter** - A class should know only its direct dependencies.
- **Avoid util classes** keep behavior logic close to the data it acts on
- Pay attention to using the right data structures

### Method Design
- Small methods - Ideal length: ~3 lines, rarely more than 5
- Avoid one-line methods unless they are being used
- Each method represents a single **atomic step of logic**
- Method names should describe *what* they do (not *how*)
- Have no side effects
- **Use early returns** use guard clauses, expensive operations later
- Avoid nested ifs/loops
- **Don’t use flag arguments**. Split method into several independent methods that can be called from the client without the flag.
- Split Validations into dedicated private methods (e.g. `validateSignatureConfiguration()`)
- Better to have many functions than to pass some code into a function to select a behavior
- Extract complex predicate logic into well-named boolean methods. e.g. `isTemplateDeletionNeeded()`

### Test Design
- small, specific, isolated
- Readable
- Fast
- Independent
- Repeatable
- Always write unit tests for new public behavior.
- Stick to fewer assertions per test. **Maximum**: 3
- Adhere to _Arrange-Act-Assert_ format
- **Avoid duplicated code** - extract commonly used data at class level
- Avoid `lenient()` unless retrofitting legacy tests; prefer explicit mocks.
- For new APIs, add integration test against controller + service + repository slice.
- Remove tests that only verify trivial getters or Spring annotations.
- Validate mandatory request fields added during changes.
- At least one negative test per API.

### Naming Guidelines
- Choose descriptive and unambiguous names.
    - Methods: verbs or verb phrases
    - Classes/DTOs: nouns
    - Variables: clear names (avoid single letters except in trivial loops)
- Prefer smaller names, but also feel free to go with longer names if it expresses intent better
- Make meaningful distinctions
- Use pronounceable names.
- Use searchable names.
- Avoid encodings. Don’t append prefixes or type information.

### Guidelines for Comments
- Always try to explain yourself in code instead of comments
- Comments explain *why*, not *what* (if code is clear)
- Document assumptions, invariants, edge cases.
- Don't be redundant
- Don't add obvious noise
- Don't use closing brace comments
- Don't comment out code. Just remove when unused
- Use as an explanation of intent
- Use as clarification of code
- Use as a warning of consequences

### SourceCode Formatting Etiquette
- Separate concepts vertically.
- Related code should appear vertically dense.
- Declare variables close to their usage.
- Dependent functions should be close
- Similar functions should be close.
- Keep private methods to be grouped at the bottom of the class for scanability
- Place functions in the downward direction.
- Keep lines short.
- Don’t use horizontal alignment.
- Use white space to associate related things and disassociate weakly related.
- Don’t break indentation.

### API Design
- Use domain-driven design (DDD) for API design
- Follow RESTful conventions
- Consistent resource naming and parameters
- REST endpoints to use nouns rather than verbs (recommended pattern: `noun/sub-resource`)
- Use plural noun resources: `/v1/templates`, add sub-resources `/defaults`, `/bulk-delete`.
- Accept filtering & expansion parameters to reduce endpoint proliferation.
- Ensure tenant/market context is always explicit on requests affecting tenant data. No duplicate/overlapping endpoints.
- Avoid wrapper DTO if single collection or simple scalar is returned.

### Collections and Stream Usage
- Minimize passes; combine filtering & mapping where readable.
- Use typed collections and avoid duplicate traversals while streaming
- Use `Stream.toList()` (immutability accepted) or ensure mutability intentionally with `Collectors.toCollection(ArrayList::new)`.
- Prefer `Set` when uniqueness required; document ordering requirements.
- Use descriptive comparator: `.sorted(Comparator.comparing(Entity::getId).reversed())`.
- Favour unmodifiable collections when exposing read-only views
- Extract complex stream chains into named methods.

### Error Handling Guidelines
- **Exception and Error Localization**: Use domain specific custom exceptions
- Externalise all user facing messages
- Handle exceptions gracefully
- maintain an ErrorCode mapping: internal log message + translation key
- Avoid swallowing exceptions; propagate or log appropriately
- Special characters validated for FrontEnd constraints

### Null Handling
- Use `Optional` judiciously for presence semantics, not to avoid null handling entirely
- Use `Optional` at API boundaries, not for internal fields unless modeling absence.
- Extract potentially null-derived values once: `var modificationType = actionProgress.getModificationType();`
- Prefer `Objects.requireNonNull` for mandatory constructor params.

### Logging Guidelines
- Log all Business events, not technical noise
- Well-structured logs
- Write meaningful log-entries
- Use appropriate log levels (INFO for lifecycle, WARN for recoverable anomalies, ERROR for failures)
- No secrets or sensitive information in logs
- Centralise log message constants for reusability and consistency

### Security Guidelines
- No hardcoded secrets, URLs, profiles or any sensitive information
- All sensitive config resolved via environment or config server.

### Configuration Management
- Externalize configuration (e.g. application.yml + environment variables + Spring Cloud Config if available)
- Use placeholders / dummy values in examples and tests.
- Remove hard-coded profiles from base YAML (avoid forcing local profile).
- Maintain BOM (Bill of Materials) for dependency version alignment & vulnerability fixes.

### Dependency & Version Management
- Centralize versions in BOM repository. Service repos reference managed versions only.
- Remove unused dependencies promptly.
- Get explicit confirmation from user before making any upgrades. When upgrading any dependency/library, strictly limit to minor or patch version upgrades only.
- Add CVE remediation notes in changelogs.

### Performance Guidelines
- **IMP**: Avoid premature micro-optimizations unless profiled.
- Batch deletes / updates in a single transaction
- Analyze complexity; refactor nested loops into indexed maps or grouping (convert to `O(n+m)`)
- Leverage JPA batch operations or bulk repository methods.
- Cache repeated derived values (`modificationType`) outside loops.

### Internationalization (i18n) & Localization
- Use translation keys in exceptions & validations; FrontEnd resolves localized text.
- Normalize characters where system limitations exist.
- Keep a consolidated `messages.properties` and language-specific overrides.
- Handle special characters (German umlauts replaced with `ae`, `oe`, `ue`)

### Bulk & Batch Operations
- Accumulate affected entity IDs, perform single update where possible.
- Wrap batch modifications in one transactional boundary.
- Use repository custom bulk operations (`deleteAllByIdInBatch`)

### Market / Tenant Awareness (also copy to tech architecture doc)
- Require market context early; validate format and authorization first.
- Provide utility for market code normalization & lookup.
- Avoid duplicating market logic in multiple services; centralize.

### Architectural Smells to identify and remove
- **Rigidity** The software is difficult to change. A small change causes a cascade of subsequent changes.
- **Fragility** The software breaks in many places due to a single change.
- **Immobility** You cannot reuse parts of the code in other projects because of the involved risks and high effort.