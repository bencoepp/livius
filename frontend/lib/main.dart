import 'package:flutter/material.dart';
import 'package:frontend/screens/globe.dart';
import 'package:frontend/screens/home.dart';
import 'package:frontend/screens/timeline.dart';
import 'package:go_router/go_router.dart';

void main() {
  runApp(const MyApp());
}

final GoRouter _router = GoRouter(
  routes: <RouteBase>[
    GoRoute(
      path: '/',
      builder: (BuildContext context, GoRouterState state) {
        return const HomePage();
      },
    ),
    GoRoute(
      path: '/timeline',
      builder: (BuildContext context, GoRouterState state) {
        return const TimelinePage();
      },
    ),
    GoRoute(
      path: '/globe',
      builder: (BuildContext context, GoRouterState state) {
        return const GlobePage();
      },
    ),
  ],
);

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      title: 'Livius',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
          colorScheme: const ColorScheme.dark(
              background: Color.fromARGB(255, 32, 32, 32),
              primary: Color.fromARGB(255, 44, 182, 158),
              primaryContainer: Color.fromARGB(255, 44, 44, 44)),
          appBarTheme:
              AppBarTheme(backgroundColor: Color.fromARGB(255, 32, 32, 32))),
      routerConfig: _router,
    );
  }
}
