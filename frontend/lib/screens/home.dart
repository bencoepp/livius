import 'package:flutter/material.dart';
import 'package:flutter_adaptive_scaffold/flutter_adaptive_scaffold.dart';
import 'package:frontend/widgets/navdrawer.dart';
import 'package:frontend/widgets/user/user_card.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home'),
      ),
      drawer: const NavDrawer(currentPage: '/'),
      body: AdaptiveLayout(
        body: SlotLayout(
          config: <Breakpoint, SlotLayoutConfig>{
            Breakpoints.small: SlotLayout.from(
              key: const Key('Body Small'),
              builder: (context) => Column(
                children: [
                  UserCard(),
                ],
              ),
            ),
            Breakpoints.mediumAndUp: SlotLayout.from(
              key: const Key('Body Medium'),
              builder: (context) => UserCard(),
            )
          },
        ),
      ),
    );
  }
}
