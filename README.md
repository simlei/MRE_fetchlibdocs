## MRE

This is aimed at scala metals in conjunction with Scala 3.1.1, and the [scalameta/nvim-metals plugin for NVIM](https://github.com/scalameta/nvim-metals). I was missing function and object docstrings while using autocompletion. the docstrings seem to be there in regular ballon tips, however I have this seen to fail also but that's for another MRE. Principle focus is the docstrings (not: the method signature, that's there.) while browsing autocomplete. 

It assumes the nvim-metals plugin is configured globally for nvim. clone this repo and fire up NVIM and...

- edit ./MRE.scala
- in line 8, try autocompletion before the braces for `ZIO#succeed` which has a single-line docstring. This line is not shown in autocompletion mode.
- like this: ![I miss some docs here...](img_demo.png)
- logs are in ./logs/:
  - [./logs/MetalsToggleLogs.txt](./logs/MetalsToggleLogs.txt)
  - [./logs/lsp.log](./logs/lsp.log)
  - [./logs/nvim-metals.log](./logs/nvim-metals.log)

The class file for `ZIO.succeed`, fetched through sbt with `withSources()` qualifier, has a source attached that I can navigate to with `gd`, that reads:

```scala
  /**
   * Returns an effect that models success with the specified value.
   */
  def succeed[A](a: => A)(implicit trace: ZTraceElement): ZIO[Any, Nothing, A] =
    new ZIO.Succeed(() => a, trace)
```

I suppose thus, the fault lies with nvim-metals being a bit too uneager for my taste with fetching those docs.

To be clear, with keypress `K`, I get the docs, but I'd really like to see them while exploring the API using autocomplete.

**Additional info:**

Output of `:MetalsInfo`
```txt
# metals 0.11.2+166-fc2e37c1-SNAPSHOT
 Note:
   Supported Scala versions:
     Scala 3: 3.0.0, 3.0.1, 3.0.2, 3.1.0, 3.1.1, 3.1.2-RC2, 3.1.2-RC3
     Scala 2:
       2.11.12
       2.12.15, 2.12.14, 2.12.13, 2.12.12, 2.12.11, 2.12.8, 2.12.9, 2.12.10
       2.13.8, 2.13.5, 2.13.6, 2.13.7, 2.13.1, 2.13.2, 2.13.3, 2.13.4

## Current settings
```json
{
  excludedPackages = { "akka.actor.typed.javadsl", "com.github.swagger.akka.javadsl" },
  serverVersion = "0.11.2+166-fc2e37c1-SNAPSHOT",
  showImplicitArguments = true,
  superMethodLensesEnabled = true
}
```


