class Changelog {
  late String id;
  late String version;
  late String title;
  late String description;
  late List<String> changes;
  late List<String> contributors;
  late String acknowledgement;
  late String references;
  late String created;
  late String updated;

  Changelog.fromJson(Map<String, dynamic> json)
      : id = json['id'] as String,
        version = json['version'] as String,
        title = json['title'] as String,
        description = json['description'] as String,
        changes =
            (json['changes'] as List).map((item) => item as String).toList(),
        contributors = (json['contributors'] as List)
            .map((item) => item as String)
            .toList(),
        acknowledgement = json['acknowledgement'] as String,
        references = json['references'] as String,
        created = json['created'] as String,
        updated = json['updated'] as String;

  Map<String, dynamic> toJson() => {
        'id': id,
        'version': version,
        'title': title,
        'description': description,
        'changes': changes,
        'contributors': contributors,
        'acknowledgement': acknowledgement,
        'references': references,
        'created': created,
        'updated': updated,
      };
}
