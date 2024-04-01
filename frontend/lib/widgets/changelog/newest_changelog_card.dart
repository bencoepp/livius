import 'package:flutter/material.dart';
import 'package:frontend/widgets/changelog/changelog_tile.dart';

class NewestChangelogCard extends StatefulWidget {
  const NewestChangelogCard({super.key});

  @override
  State<NewestChangelogCard> createState() => _NewestChangelogCardState();
}

class _NewestChangelogCardState extends State<NewestChangelogCard> {
  @override
  Widget build(BuildContext context) {
    return const ChangelogTile();
  }
}
