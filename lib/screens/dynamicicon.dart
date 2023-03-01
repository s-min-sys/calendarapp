import 'package:calendarapp/screens/sputil.dart';
import 'package:dynamic_app_icon/dynamic_app_icon.dart';

void refreshAppIcon() {
  print("[BackgroundFetch] setAppIcon start");
  const List<String> list = [
    "MainActivity",
    "ic_launcher1",
    "ic_launcher2",
    "ic_launcher3",
    "ic_launcher4",
    "ic_launcher5",
    "ic_launcher6",
    "ic_launcher7",
    "ic_launcher8",
    "ic_launcher9",
    "ic_launcher10",
    "ic_launcher11",
    "ic_launcher12",
    "ic_launcher13",
    "ic_launcher14",
    "ic_launcher15",
    "ic_launcher16",
    "ic_launcher17",
    "ic_launcher18",
    "ic_launcher19",
    "ic_launcher20",
    "ic_launcher21",
    "ic_launcher22",
    "ic_launcher23",
    "ic_launcher24",
    "ic_launcher25",
    "ic_launcher26",
    "ic_launcher27",
    "ic_launcher28",
    "ic_launcher29",
    "ic_launcher30",
    "ic_launcher31",
  ];

  var now = DateTime.now();

  try {
    DynamicAppIcon()
        .androidSetIcon(icon: 'ic_launcher${now.day}', listAvailableIcon: list);

    // ignore: empty_catches
  } catch (e) {
    print("[DebugBackground] setAppIcon error:" + e.toString());
  }

  SpUtils.putString('day', '${now.day}');
}
