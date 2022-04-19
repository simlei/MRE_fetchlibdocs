object MRE {
   /** some docs */
  def foo(): Unit = ()

  fo // <--- works looking at the "docs" in autocompletion

  import zio.*
  ZIO.collect // <--- trigger autocompletion here, see image & situation from README
}
