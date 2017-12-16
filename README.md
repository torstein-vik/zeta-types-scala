# Zeta Types (Scala)
***Implementation of Zeta Types / Tannakian Symbols in scala*** <p>
[![Build Status](https://travis-ci.org/torstein-vik/zeta-types-scala.svg?branch=master)](https://travis-ci.org/torstein-vik/zeta-types-scala)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)


## Installation

1. Clone this repo to your computer
2. Make sure you have SBT (simple build tool) installed on your computer
3. Run 'sbt' in the main directory to start SBT
4. Inside SBT, run 'test' to test the codebase 
5. Run 'test:console' to get an interactive console

Please tell us if this doesn't work, because that means something is wrong with our instructions.

## Current features

#### Algebraic system
* Abstract classes for magmas, monoids, (partial) groups, rings, lambda-rings, and (partial) q-algebras.
* Automatic implementation of lambda-operations from adams-operations and (partial) q-algebras.

#### Implemented algebraic structures
* Integers as lambda-ring and partial q-algebra.
* Fractions for any ring (as a ring).
* Complex numbers for any coefficient-ring (as a ring).
* Polynomials for any ring (as a ring).
* Equality and toString for all of these.
* DSL bindings for polynomials and complex (rational) numbers.
* Type synonyms for integral fractions and rational complex numbers. 

#### Tannakian symbols
* Multisets, with equality and toString
* Division of multisets for creating Tannakian symbols with nice syntax
* Tannakian symbols for any monoid (implemented as a lambda-ring and partial q-algebra)
* Equality and toString for Tannakian symbols
* Tannakian symbol structure methods
  * Get upstairs and downstairs multiset
  * Super-, odd-, and even dimension
  * Augmentation
  * Whether line element
  
#### Sequences
* Abstract Sequence and SequenceFactory interface for infinite (recursive) sequences
* CachedSequence implementation of abstract Sequence
* SequenceFactory combinators
* DSL for creating and combinating sequences

#### Algebraic Sequences
* Ring of algebraic sequences
* Subclasses for special (beginning with 1) sequences
* Type synonyms for complex (special) algebraic sequences

## Usage Examples

#### Polynomials

    > implicit val ring : Ring[Integer] = Integers
    
    > (x~^5000 + 3) + (-(x~^5000) + 3*x)
      Polynomial[Integer] = 3 + 3x
      
    > (2*x + 3) * (2*x~^2 + x + 4)
      Polynomial[Integer] = 12 + 11x + 8x^2 + 4x^3

#### Tannakian symbols

    > implicit val monoid : Monoid[Integer] = Integers
    
    > ms(1, 2)/Ø + ms(1, 2)/Ø
      TannakianSymbol[Integer] = {1, 1, 2, 2}/Ø
      
    > ms(2, 2, 3, 3)/Ø lambda(3)
      TannakianSymbol[Integer] = {12, 12, 18, 18}/Ø
      
#### Sequences

    > Sequence(1 upTo 2 followedBy 3)
      CachedSequence[Int] = 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, ...
      
    > Sequence(Seq(1, 1) followedBy new RecursiveSequence(seq => i => seq(i - 1) + seq(i - 2)))
      CachedSequence[Int] = 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, ...

## Contributors

_Ask Torstein ([torsteinv64@gmail.com](mailto:torsteinv64@gmail.com)) to add you here if you contribute to this project_
* Torstein Vik

## Copyright


This framework is and will remain completely open source, under the GNU General Public License version 3+:

    Copyright (C) 2017, Torstein Vik.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    

## Languages/Frameworks

* Implementation: Scala
* Build tool: SBT

## Folder structure

* /project/ -- Part of SBT

* /src/ -- Source directory, where code is edited.
* /src/main/scala -- Main codebase
* /src/test/scala -- Test for the codebase
