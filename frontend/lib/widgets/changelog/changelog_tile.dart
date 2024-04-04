import 'package:flutter/material.dart';

class ChangelogTile extends StatefulWidget {
  const ChangelogTile({super.key});

  @override
  State<ChangelogTile> createState() => _ChangelogTileState();
}

class _ChangelogTileState extends State<ChangelogTile> {
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        child: Column(
          children: [
            ListTile(
              title: Text('TITLE'),
              subtitle: Text('DESCTIPTION'),
            ),
          ],
        ),
      ),
    );
  }
}
