import 'package:flutter/material.dart';
import 'package:flutter_adaptive_scaffold/flutter_adaptive_scaffold.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Livius',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  const HomePage({super.key});
  final _selectedTab = 0;
  @override
  Widget build(BuildContext context) {
    // Define the children to display within the body at different breakpoints.
    final List<Widget> children = <Widget>[
      for (int i = 0; i < 10; i++)
        Padding(
          padding: const EdgeInsets.all(8.0),
          child: Container(
            color: Color.fromARGB(255, 122, 39, 33),
            height: 400,
          ),
        )
    ];

    return AdaptiveScaffold(
      transitionDuration: Duration(milliseconds: 300),
      smallBreakpoint: const WidthPlatformBreakpoint(end: 700),
      mediumBreakpoint: const WidthPlatformBreakpoint(begin: 700, end: 1000),
      largeBreakpoint: const WidthPlatformBreakpoint(begin: 1000),
      useDrawer: false,
      selectedIndex: _selectedTab,
      destinations: const <NavigationDestination>[
        NavigationDestination(
          icon: Icon(Icons.inbox_outlined),
          selectedIcon: Icon(Icons.inbox),
          label: 'Inbox',
        ),
        NavigationDestination(
          icon: Icon(Icons.article_outlined),
          selectedIcon: Icon(Icons.article),
          label: 'Articles',
        ),
        NavigationDestination(
          icon: Icon(Icons.chat_outlined),
          selectedIcon: Icon(Icons.chat),
          label: 'Chat',
        ),
        NavigationDestination(
          icon: Icon(Icons.video_call_outlined),
          selectedIcon: Icon(Icons.video_call),
          label: 'Video',
        ),
        NavigationDestination(
          icon: Icon(Icons.home_outlined),
          selectedIcon: Icon(Icons.home),
          label: 'Inbox',
        ),
      ],
      body: (_) => GridView.count(crossAxisCount: 2, children: children),
      smallBody: (_) => ListView.builder(
        itemCount: children.length,
        itemBuilder: (_, int idx) => children[idx],
      ),
      secondaryBody: (_) => Container(
        color: const Color.fromARGB(255, 234, 158, 192),
      ),
      smallSecondaryBody: AdaptiveScaffold.emptyBuilder,
    );
  }
}
