package Quarter_2;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lasagna {

	public static void main(String[] args) {
		System.out.println(mix("Are they here", "yes, they are here"));
		System.out.println(mix("looping is fun but dangerous", "less dangerous than coding"));
	}

	public static String mix(String s1, String s2) {
//		ArrayList<String> arrayList = new ArrayList<>(); // Don't try to do everything in streams first, the code will get much messier and slower
		TreeSet<String> treeSet = new TreeSet<>();
		for (char c = 'a'; c <= 'z'; c++) {
			String s1_char = s1.replaceAll("[^" + c + "]+","");
			String s2_char = s2.replaceAll("[^" + c + "]+","");
			int s1_length = s1_char.length();
			int s2_length = s2_char.length();
			if (s1_length > 1 || s2_length > 1)
				if (s1_length == s2_length)
					treeSet.add("=:" + s1_char);
				else if (s1_length > s2_length)
					treeSet.add("1:" + s1_char);
				else if (s1_length < s2_length)
					treeSet.add("2:" + s2_char);
		}
		Comparator<String> length = (x,y) -> y.length()-x.length();
		Comparator<String> type_value = Comparator.comparingInt(x -> x.charAt(0));
		return treeSet.stream().sorted(length.thenComparing(type_value)).collect(Collectors.joining("/"));
	}

//	public static String mix(String s1, String s2) {
//		return Stream.of(parse(s1, 1), parse(s2, 2))
//				.flatMap(stringMap -> stringMap.entrySet().stream())
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						Map.Entry::getValue,
//						(integers, integers2) -> {
//							if (integers[0] > integers2[0])
//								return integers;
//							if (integers[0] < integers2[0])
//								return integers2;
//							return new Integer[] {integers[0], 3};
//						}))
//				.entrySet().stream()
//				.filter(stringEntry -> stringEntry.getValue()[0] > 1)
//				.sorted(Comparator.comparingInt((Map.Entry<String, Integer[]> o) -> -o.getValue()[0]).thenComparing(o -> (o.getValue()[1] + ":" + o.getKey())))
//				.map(stringEntry -> (stringEntry.getValue()[1] == 3 ? "=" : stringEntry.getValue()[1]) + ":" + new String(new char[stringEntry.getValue()[0]]).replace("\0", stringEntry.getKey()))
//				.collect(Collectors.joining("/"));
//	}
//
//	public static Map<String, Integer[]> parse(String s, Integer i) {
//		return Stream.of(s.replaceAll("[^a-z]", "")
//				.split(""))
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//				.entrySet().stream()
//				.collect(Collectors.toMap(Map.Entry::getKey, stringLongEntry -> new Integer[] {Math.toIntExact(stringLongEntry.getValue()), i}));
//	}

// ACTUALLY WORKING VERSION

//public static String mix(String s1, String s2) {
//	System.out.println(s1 + " " + s2);
//	Map<String, Integer[]> a = parse(s1, 1);
//	Map<String, Integer[]> b = parse(s2, 2);
//	Map<String, Integer[]> c = Stream.of(a, b)
//			.flatMap(stringMap -> stringMap.entrySet().stream())
//			.collect(Collectors.toMap(
//					Map.Entry::getKey,
//					Map.Entry::getValue,
//					(integers, integers2) -> {
//						if (integers[0] > integers2[0])
//							return integers;
//						if (integers[0] < integers2[0])
//							return integers2;
//						return new Integer[] {integers[0], 3};
//					}));
//	String d = c.entrySet().stream()
//			.filter(stringEntry -> stringEntry.getValue()[0] > 1)
//			.sorted(Comparator.comparingInt((Map.Entry<String, Integer[]> o) -> -o.getValue()[0]).thenComparing(o -> (o.getValue()[1] + ":" + o.getKey())))
//			.map(stringEntry -> (stringEntry.getValue()[1] == 3 ? "=" : stringEntry.getValue()[1]) + ":" + new String(new char[stringEntry.getValue()[0]]).replace("\0", stringEntry.getKey()))
//			.collect(Collectors.joining("/"));
//	return d;
//}
//
//	public static Map<String, Integer[]> parse(String s, Integer i) {
//		String[] a = s.replaceAll("[^a-z]", "").split("");
//		Map<String, Long> b = Stream.of(a).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		Map<String, Integer[]> c = b.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, stringLongEntry -> new Integer[] {Math.toIntExact(stringLongEntry.getValue()), i}));
//		return c;
//	}


// Working Version Below

//	public static String mix(String s1, String s2) {
//		var a = parse(s1, 1);
//		var b = parse(s2, 2);
//		var c = Stream.of(a, b)
//				.flatMap(stringMap -> stringMap.entrySet().stream())
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						Map.Entry::getValue,
//						(integers, integers2) -> {
//							System.out.println(Arrays.toString(integers) + " " + Arrays.toString(integers2));
//							if (integers[0] > integers2[0])
//								return integers;
//							if (integers[0] < integers2[0])
//								return integers2;
//							return new Integer[] {integers[0], 0};
//						}));
//		var d = c.entrySet().stream()
//				.filter(stringEntry -> stringEntry.getValue()[0] > 1)
//				.sorted(Comparator.comparingInt((Map.Entry<String, Integer[]> o) -> -o.getValue()[0]).thenComparingInt(o -> -o.getValue()[1]).thenComparing(Map.Entry::getKey))
//				.map(stringEntry -> (stringEntry.getValue()[1] == 0 ? "=" : stringEntry.getValue()[1]) + ":" + new String(new char[stringEntry.getValue()[0]]).replace("\0", stringEntry.getKey()))
//				.collect(Collectors.joining("/"));
//		return d;
//	}
//
//	public static Map<String, Integer[]> parse(String s, Integer i) {
//		var a = s.replaceAll("[^a-z]", "").split("");
//		var b = Stream.of(a).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		var c = b.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, stringLongEntry -> new Integer[] {Math.toIntExact(stringLongEntry.getValue()), i}));
//		return c;
//	}

// SOMEWHAT WORKING VERSION BELOW

//	public static String mix(String s1, String s2) {
//		Map<String, Integer[]> a = parse(s1, 1);
//		Map<String, Integer[]> b = parse(s2, 2);
//		Map<String, Integer[]> c = Stream.of(a, b)
//				.flatMap(stringMap -> stringMap.entrySet().stream())
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						Map.Entry::getValue,
//						(integers, integers2) -> {
//							System.out.println(Arrays.toString(integers) + " " + Arrays.toString(integers2));
//							if (integers[0] > integers2[0])
//								return integers;
//							if (integers[0] < integers2[0])
//								return integers2;
//							return new Integer[] {integers[0], 0};
//						}));
//		String d = c.entrySet().stream()
//				.filter(stringEntry -> stringEntry.getValue()[0] > 1)
//				.sorted(Comparator.comparingInt((ToIntFunction<Map.Entry<String, Integer[]>>) o -> -o.getValue()[0]).thenComparing(Map.Entry::getKey))
//				.map(stringEntry -> (stringEntry.getValue()[1] == 0 ? "=" : stringEntry.getValue()[1]) + ":" + new String(new char[stringEntry.getValue()[0]]).replace("\0", stringEntry.getKey()))
//				.collect(Collectors.joining("/"));
//		return d;
//	}
//
//	public static Map<String, Integer[]> parse(String s, int i) {
//		String[] a = s.replaceAll("[^a-z]", "").split("");
//		Map<String, Long> b = Stream.of(a).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		Map<String, Integer[]> c = b.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, stringLongEntry -> new Integer[] {Math.toIntExact(stringLongEntry.getValue()), i}));
//		return c;
//	}

//				(strings, strings2) -> {
//					if (strings[0] > strings2[0])
//						return strings;
//					if (strings[0] < strings2[0])
//						return strings2;
//					return new String[] {"=:" + strings[0].substring(2)};
//				}));
//		var d = c.entrySet().stream().sorted(new Comparator<Map.Entry<String, String[]>>() {
//			@Override
//			public int compare(Map.Entry<String, String[]> o1, Map.Entry<String, String[]> o2) {
//				return o1.getValue()[0].length();
//			}
//		});

}
