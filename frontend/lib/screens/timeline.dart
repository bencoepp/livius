import 'package:fluent_ui/fluent_ui.dart';
import 'package:go_router/go_router.dart';
import '../widgets/page.dart';

class TimelinePage extends StatefulWidget {
  const TimelinePage({super.key});

  @override
  State<TimelinePage> createState() => _TimelinePageState();
}

class _TimelinePageState extends State<TimelinePage> with PageMixin {
  bool selected = true;
  String? comboboxValue;

  @override
  Widget build(BuildContext context) {
    assert(debugCheckHasFluentTheme(context));
    return ScaffoldPage.scrollable(
      header: PageHeader(
        title: const Text('Timeline'),
        commandBar: Row(mainAxisAlignment: MainAxisAlignment.end, children: [
          Tooltip(
            message: "Search",
            child: SizedBox(
                width: 200,
                child: TextBox(
                  placeholder: "Search...",
                )),
          )
        ]),
      ),
      children: const [],
    );
  }
}
