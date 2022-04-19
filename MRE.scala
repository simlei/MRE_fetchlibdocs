object MRE {
   /** some docs */
  def foo(): Unit = ()

  fo // <--- works looking at the "docs" in autocompletion

  import zio.*
  val x = ZIO.succeed() // <--- trigger autocompletion somewhere before the braces, see image & situation from README
}
