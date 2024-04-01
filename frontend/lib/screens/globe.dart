import 'package:flutter/material.dart';
import 'package:frontend/widgets/navdrawer.dart';

class GlobePage extends StatefulWidget {
  const GlobePage({super.key});

  @override
  State<GlobePage> createState() => _GlobePageState();
}

class _GlobePageState extends State<GlobePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Globe'),
      ),
      drawer: const NavDrawer(currentPage: '/globe'),
      body: const Center(
        child: Text(
          'Welcome to your Flutter base project!',
          style: TextStyle(fontSize: 20.0),
        ),
      ),
    );
  }
}
