
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/coreycaplan/Idea Projects/csb-voting-app/conf/routes
// @DATE:Wed Mar 29 16:27:54 EDT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
