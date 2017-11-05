package org.torsteinv

trait ProjectPackage 
    extends zetatypes.algebraPackage
    with zetatypes.algebra.structuresPackage
    with zetatypes.sequencesPackage
    with zetatypes.tannakiansymbolsPackage

package object ZetaType extends ProjectPackage

package object ZetaTypeDSL extends ProjectPackage
    with zetatypes.algebra.structures.DSLPackaging
    with zetatypes.tannakiansymbols.DSLPackaging
    with zetatypes.sequences.DSLPackaging
