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
        floatingActionButton: FloatingActionButton(
          onPressed: () {},
          child: const Icon(Icons.settings),
        ),
        body: SizedBox(
          height: MediaQuery.of(context).size.height,
          child: ListView(
            scrollDirection: Axis.horizontal,
            children: <Widget>[
              Container(
                width: 160.0,
                color: Colors.red,
              ),
            ],
          ),
        ));
  }
}
