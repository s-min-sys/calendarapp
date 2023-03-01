import 'package:calendarapp/screens/splash.dart';
import 'package:dynamic_app_icon/dynamic_app_icon.dart';
import 'package:flutter/material.dart';
import 'package:intl/date_symbol_data_local.dart';
import 'package:calendarapp/screens/sputil.dart' show SpUtils;

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();

  initializeDateFormatting().then((_) => {
        runApp(const MainApp()),
        loadAsync(),
        DynamicAppIcon().androidSendBroadcast(
            action: 'com.example.calendarapp.REFRESH_ICON'),
        DynamicAppIcon().androidSendBroadcast(
            action: 'com.example.calendarapp.REFRESH_ALARM'),
      });
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: Scaffold(
        body: SplashPage(),
      ),
    );
  }
}

void loadAsync() async {
  await SpUtils.getInstance();
}
