import 'package:fluent_ui/fluent_ui.dart';
import 'package:go_router/go_router.dart';
import '../widgets/page.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> with PageMixin {
  bool selected = true;
  String? comboboxValue;

  @override
  Widget build(BuildContext context) {
    assert(debugCheckHasFluentTheme(context));
    return ScaffoldPage.scrollable(
      header: PageHeader(
        title: const Text('Home'),
        commandBar: Row(mainAxisAlignment: MainAxisAlignment.end, children: []),
      ),
      children: const [],
    );
  }
}
