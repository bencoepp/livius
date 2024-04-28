import 'dart:convert';

import 'package:frontend/model/user/role.dart';
import 'package:frontend/model/user/token.dart';
import 'package:http/http.dart' as http;

class User {
  late String id;
  late String username;
  late String email;
  late String password;
  late List<Role> roles;

  User()
      : id = '',
        username = 'John Doe',
        email = 'john.doe@livius.io',
        password = 'TestLivius123!';

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

  static Future<User> getMe() async {
    var token = await Token.get();
    http.Response response = await http.get(
      Uri.parse('https://siff.dev/snippet/api/snippet/all'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'Authorization': "Bearer ${token.token}"
      },
    );

    if (response.statusCode == 200) {
      return User.fromJson(jsonDecode(response.body));
    } else {
      return User();
    }
  }
}
