import 'dart:convert';

import 'package:shared_preferences/shared_preferences.dart';

class Token {
  late String token;
  late String type;
  late String id;
  late String username;
  late String email;

  Token();

  Token.fromJson(Map<String, dynamic> json)
      : token = json['token'] as String,
        type = json['type'] as String,
        id = json['id'] as String,
        username = json['username'] as String,
        email = json['email'] as String;

  Map<String, dynamic> toJson() => {
        'token': token,
        'type': type,
        'id': id,
        'username': username,
        'email': email,
      };

  static Future<Token> get() async {
    try {
      final SharedPreferences storage = await SharedPreferences.getInstance();
      return Token.fromJson(jsonDecode(storage.getString("token")!));
    } catch (e) {
      return Token();
    }
  }
}
