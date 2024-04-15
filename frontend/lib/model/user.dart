import 'dart:convert';

import 'package:frontend/model/role.dart';

class User {
  late String id;
  late String username;
  late String email;
  late String password;
  late List<Role> roles;

  User.fromJson(Map<String, dynamic> json)
      : id = json['id'] as String,
        username = json['username'] as String,
        email = json['email'] as String,
        password = json['password'] as String,
        roles = List<Role>.from(
            jsonDecode(json['roles']).map((model) => Role.fromJson(model)));

  Map<String, dynamic> toJson() => {
        'id': id,
        'username': username,
        'email': email,
        'password': password,
        'roles': roles,
      };
}
