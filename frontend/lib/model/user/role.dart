class Role {
  late String id;
  late String name;

  Role(this.id, this.name);

  Role.fromJson(Map<String, dynamic> json)
      : id = json['id'] as String,
        name = json['name'] as String;

  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
      };
}
