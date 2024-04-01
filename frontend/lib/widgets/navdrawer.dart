import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class NavDrawer extends StatefulWidget {
  const NavDrawer({super.key, required this.currentPage});

  final String currentPage;

  @override
  State<NavDrawer> createState() => _NavDrawerState();
}

class _NavDrawerState extends State<NavDrawer> {
  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: ListView(
      padding: EdgeInsets.zero,
      children: [
        NavDrawerItem(
          title: 'Home',
          route: '/',
          currentPage: widget.currentPage,
          icon: Icons.home,
        ),
        NavDrawerItem(
          title: 'Timeline',
          route: '/timeline',
          currentPage: widget.currentPage,
          icon: Icons.view_timeline_outlined,
        ),
        NavDrawerItem(
          title: 'Globe',
          route: '/globe',
          currentPage: widget.currentPage,
          icon: Icons.assistant_photo_outlined,
        ),
      ],
    ));
  }
}

class NavDrawerItem extends StatelessWidget {
  const NavDrawerItem(
      {super.key,
      required this.title,
      required this.route,
      required this.currentPage,
      required this.icon});

  final String title;
  final String route;
  final String currentPage;
  final IconData icon;
  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(title),
      selected: currentPage == route,
      leading: Icon(icon),
      onTap: () => context.go(route),
    );
  }
}
