import 'package:flutter/material.dart';
import 'package:frontend/widgets/changelog/changelog_tile.dart';

class NewsletterList extends StatefulWidget {
  const NewsletterList({super.key});

  @override
  State<NewsletterList> createState() => _NewsletterListState();
}

class _NewsletterListState extends State<NewsletterList> {
  @override
  Widget build(BuildContext context) {
    return const ChangelogTile();
  }
}
