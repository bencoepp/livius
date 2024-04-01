import 'package:flutter/material.dart';
import 'package:frontend/widgets/navdrawer.dart';

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
      body: const Center(
        child: Text(
          'Welcome to your Flutter base project!',
          style: TextStyle(fontSize: 20.0),
        ),
      ),
    );
  }
}
