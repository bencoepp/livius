import 'package:flutter/material.dart';
import 'package:frontend/widgets/navdrawer.dart';

class TimelinePage extends StatefulWidget {
  const TimelinePage({super.key});

  @override
  State<TimelinePage> createState() => _TimelinePageState();
}

class _TimelinePageState extends State<TimelinePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Timeline'),
      ),
      drawer: const NavDrawer(currentPage: '/timeline'),
      body: const Center(
        child: Text(
          'Welcome to your Flutter base project!',
          style: TextStyle(fontSize: 20.0),
        ),
      ),
    );
  }
}
