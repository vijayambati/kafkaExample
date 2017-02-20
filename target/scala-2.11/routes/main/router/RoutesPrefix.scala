
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/vijayambati/vijayws/play-rest-api/conf/routes
// @DATE:Sun Feb 19 22:54:43 CST 2017


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
