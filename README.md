# What2Eat

Sample app for demonstrating architecture design in SWAR lecture (HTWG WIN)

There are two versions of the app in different branches:

- branch [initial](https://github.com/neshanjo/what2eat/tree/initial) with the first version (no caching, dependencies
  hard-wired)
- branch [with-di](https://github.com/neshanjo/what2eat/tree/with-di) with a version that uses dependency injection for
  better testability.
- branch [with-cache](https://github.com/neshanjo/what2eat/tree/with-cache) with a further advanced version that caches
  weather and menu requests for a better performance. **This version also contains a detailed [architecture documentation](https://github.com/neshanjo/what2eat/blob/with-cache/doc/architecture-documentation.md)**.
- branch [with-spring-boot](https://github.com/neshanjo/what2eat/tree/with-spring-boot) is based on with-di, but uses the Spring Boot framework.

For more information, see the README file in the branches.

Please do no use the code without the permission of the author. If you find it useful for your lecture or project, just contact me.
