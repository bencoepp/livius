import 'package:flutter/material.dart';

class NewsletterTile extends StatefulWidget {
  const NewsletterTile({super.key});

  @override
  State<NewsletterTile> createState() => _NewsletterTileState();
}

class _NewsletterTileState extends State<NewsletterTile> {
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
