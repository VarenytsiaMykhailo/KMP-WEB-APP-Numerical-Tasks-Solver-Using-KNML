package presentation.navigation.route

sealed interface Route {
    val name: String
        get() = this::class.simpleName!!
    val routeName: String
        get() = this::class.qualifiedName!!
}

sealed class NavigationStartRouteGroup : Route {
    data object RootScreen : NavigationStartRouteGroup()
}

sealed class RootScreenRoutes : Route {
    data object SystemSolvingMethods : RootScreenRoutes()
    data object IntegralMethods : RootScreenRoutes()
    data object QueryMethod : RootScreenRoutes()
}

sealed class SystemSolvingMethodsScreenRoutes : Route {
    data object GaussClassicMethod : SystemSolvingMethodsScreenRoutes()
    data object GaussPivotingMethod : SystemSolvingMethodsScreenRoutes()
    data object SeidelMethod : SystemSolvingMethodsScreenRoutes()
    data object JacobiMethod : SystemSolvingMethodsScreenRoutes()
    data object ThomasMethod : SystemSolvingMethodsScreenRoutes()
}

sealed class IntegralMethodsScreenRoutes : Route {
    data object SimpsonMethod : IntegralMethodsScreenRoutes()
    data object TrapezoidMethod : IntegralMethodsScreenRoutes()
    data object RectangleMethod : IntegralMethodsScreenRoutes()
}

sealed class OtherScreenRoutes: Route {
    data object SolutionScreen: OtherScreenRoutes()
    data object SelectRouteOptionScreen: OtherScreenRoutes()
}
