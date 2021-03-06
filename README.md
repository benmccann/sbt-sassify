# Sass plugin for sbt

An sbt plugin that enables you to use [Sass](http://sass-lang.com/) in your [sbt-web](https://github.com/sbt/sbt-web)
project.

This plugin is a reimplementation of [sbt-sass](https://github.com/ShaggyYeti/sbt-sass).
Since I wasn't allowed to install the sass command line compiler on my company's' webserver (damn you corporate IT),
I decided to rewrite the plugin to use [jsass](https://github.com/bit3/jsass) instead. Due to these changes, the plugin
no longer resembled the old plugin, which is why I decided to host it myself.

## Sass language version
This plugin is based on [libsass](https://github.com/sass/libsass) version 3.3.2, that implements the Sass 3.4 specification.

## Compatibility
The sbt-sassify plugin supports the following operating systems:
- OS X 10.8+
- Windows (32/64 bit)
- Linux 64 bit

Note that it requires Java 8. Additionally, this plugin has been tested against sbt-web and the Play framework versions 1.2.2 and 2.4.3+ respectively.

## Usage

To use the `sbt-sassify` plugin you can include the plugin in `project/plugins.sbt` or `project/sbt-sassify.sbt` like this:

    addSbtPlugin("org.irundaia.sbt" % "sbt-sassify" % "1.3.1")

### Directory structure

This plugin uses the same conventions as sbt-web. As such all `*.sass` and `*.scss` files in the `<source dir>/assets`
directory will be compiled. Depending on the extension of the file, the plugin will decide which syntax should be used
to compile the source file. `.sass` for the indented syntax and `.scss` for the css-like syntax. (Note that the input
style can be forced. See the `syntaxDetection` option.)

### Options

Some options can be passed to the Sass compiler. For an overview, see below:

| Setting            | Description                                                          | Supported values               | Default value |
|--------------------|----------------------------------------------------------------------|--------------------------------|---------------|
| cssStyle           | The style of the output CSS file.                                    | `Minified`/`Maxified`/`Sassy`  | `Minified`    |
| generateSourceMaps | Whether or not source files are generated.                           | `true`/`false`                 | `true`        |
| embedSources       | Whether or not the sources should be embedded in the source map file | `true`/`false`                 | `true`        |
| syntaxDetection    | How to determine whether the sass/scss syntax is used                | `Auto`/`ForceScss`/`ForceSass` | `Auto`        |

Changing the settings can be done by including the following settings in your build.sbt file:

```scala
import org.irundaia.sbt.sass._

SassKeys.cssStyle := Maxified

SassKeys.generateSourceMaps := true

SassKeys.syntaxDetection := ForceScss
```

## Versioning
sbt-sassify uses [semantic versioning](http://semver.org). Given a version number `major.minor.patch`, an increment in

- `major` signifies a non-backwards-compatible API change;
- `minor` signifies a backwards-compatible functionality implementation;
- `patch` signifies a backwards-compatible bug fix/refactoring.

## Known limitations

1. Source maps can only reflect files in the assets folder.

2. Only one Sass syntax style can be used at the same time. So when compiling a .scss file, one cannot include a .sass
  file. (Well, you can, but it won't compile.)