/*
 * Copyright 2015 Han van Venrooij
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.irundaia.sbt.sass.compiler

import io.bit3.jsass.OutputStyle
import org.irundaia.sbt.sass._

case class CompilerSettings(style: CssStyle, generateSourceMaps: Boolean, embedSources: Boolean, syntaxDetection: SyntaxDetection) {

  def compilerStyle: OutputStyle = style match {
    case Minified => OutputStyle.COMPRESSED
    case Maxified => OutputStyle.EXPANDED
    case Sassy => OutputStyle.NESTED
  }

  def isIndented(fileName: String): Boolean = syntaxDetection match {
    case Auto => fileName.endsWith("sass")
    case ForceSass => true
    case ForceScss => false
  }
}