import 'package:flutter/material.dart';
import 'package:frontend/widgets/navdrawer.dart';

class SettingsPage extends StatefulWidget {
  const SettingsPage({super.key});

  @override
  State<SettingsPage> createState() => _SettingsPageState();
}

class _SettingsPageState extends State<SettingsPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Settings'),
      ),
      drawer: const NavDrawer(currentPage: '/settings'),
      body: const Center(
        child: Padding(
          padding: EdgeInsets.all(8.0),
          child: Text(
            'The settings are currently under production and should not be used until further notice.',
            style: TextStyle(fontSize: 20.0),
          ),
        ),
      ),
    );
  }
}
