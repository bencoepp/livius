import 'package:flutter/material.dart';
import 'package:frontend/widgets/changelog/changelog_tile.dart';

class ChangelogList extends StatefulWidget {
  const ChangelogList({super.key});

  @override
  State<ChangelogList> createState() => _ChangelogListState();
}

class _ChangelogListState extends State<ChangelogList> {
  @override
  Widget build(BuildContext context) {
    return const ChangelogTile();
  }
}
