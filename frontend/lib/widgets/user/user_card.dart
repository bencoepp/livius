import 'package:flutter/material.dart';
import 'package:frontend/model/user/user.dart';

class UserCard extends StatefulWidget {
  const UserCard({super.key});

  @override
  State<UserCard> createState() => _UserCardState();
}

class _UserCardState extends State<UserCard> {
  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: User.getMe(),
        builder: (context, snapshot) {
          if (snapshot.hasData &&
              snapshot.connectionState == ConnectionState.done) {
            return Padding(
              padding: const EdgeInsets.all(8.0),
              child: Card(
                child: Column(
                  children: [
                    ListTile(
                      title: Text(snapshot.data!.username),
                      subtitle: Text(snapshot.data!.id),
                    ),
                  ],
                ),
              ),
            );
          } else {
            return const Padding(
              padding: EdgeInsets.all(8.0),
              child: Card(
                child: Center(
                  child: Text("NO CURRENT USER DATA"),
                ),
              ),
            );
          }
        });
  }
}
