package Quarter_2;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodeWars {

//    public static boolean isReallyInteresting(int number, final int[] awesomePhrases) {
//
////        return Stream.<Predicate<String>>of(
////                s -> {
////                    System.out.println(s);
////                    System.out.println(parseInt(s));
////                    System.out.println(s.getClass());
////                    return false;
////                },
////                s -> s.matches("\\d0+"), // Has trailing zeroes
////                s -> new StringBuilder(s).reverse().toString().equals(s), // is palindrome
////                "1234567890"::contains, // is increasing
////                "9876543210"::contains, // is decreasing
////                s -> Arrays.stream(awesomePhrases).anyMatch(n -> parseInt(s) == n) // matches awesome phrases
////        ).anyMatch(p -> number > 99 && p.test(Integer.toString(number))); //
//
//        return Stream.<Predicate<Integer>>of(
//                n ->
////                s -> s.matches("\\d0+"), // Has trailing zeroes
//                        s -> new StringBuilder(s).reverse().toString().equals(s), // is palindrome
////                "1234567890"::contains, // is increasing
////                "9876543210"::contains, // is decreasing
//                s -> Arrays.stream(awesomePhrases).anyMatch(n -> s == n) // matches awesome phrases
//        ).anyMatch(p -> number > 99 && p.test(number)); //
//    }
//
//    public static int isInteresting(int number, int[] awesomePhrases) {
//        BiPredicate<Integer, int[]> predicate = aBoolean -> Stream.<Predicate<String>>of(
//                s -> s.matches("\\d0+"), // Has trailing zeroes
//                s -> new StringBuilder(s).reverse().toString().equals(s), // is palindrome
//                "1234567890"::contains, // is increasing
//                "9876543210"::contains, // is decreasing
//                s -> Arrays.stream(awesomePhrases).anyMatch(n -> parseInt(s) == n) // matches awesome phrases
//        ).anyMatch(p -> number > 99 && p.test(Integer.toString(number)));
////        return isReallyInteresting(number, awesomePhrases) ? 2 :
////                isReallyInteresting(number + 1, awesomePhrases) ? 1 :
////                        isReallyInteresting(number + 2, awesomePhrases) ? 1 : 0;
//        return predicate.test(number, awesomePhrases) ? 2 : 0;
//    }
//    static int result = 0;

//	static String function(List<String> strings) {
//		var a = Stream.of(strings.toArray(new String[0])).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : 0);
//		var map = Stream.of(strings.toArray(new String[0])).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		var a =	map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
//		var b = a.isPresent() ? a.get().getValue() : -1;
//		var c = map.entrySet().stream().filter(stringLongEntry -> stringLongEntry.getValue() == b).collect(toList());

//		Map<String, Long> map = Stream.of(strings.toArray(new String[0])).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		Map<String, Long> map = strings.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		var a = map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get();
//		a = strings.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get();
////		return a.getValue() > strings.size() / 2 ? a.getKey() : "";
//
//
//		return map.entrySet().stream().filter(stringLongEntry -> stringLongEntry
//						.getValue()
//						.equals(a.getValue()))
//				.collect(Collectors.toList()).size() != 1 ? null : a.getKey();
//		System.out.println(b);

//		System.out.println(cats.get(1);
//				.filter(stringLongEntry -> {
//			System.out.println(stringLongEntry + "uheifuw");
//			return stringLongEntry == longest;
//			return false;
//		})//.collect(toList());;
//		var a = Stream.of(strings.toArray(new String[0])).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max()
//		System.out.println(cats.get(1);
//		return a.map(Map.Entry::getKey).orElse(null);
//		Map<String, Long> map =  Stream.of(strings.toArray(new String[0])).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		map.entrySet().stream().max(map.values())
//		Collections.max(map)
//		return "";
//	}

//	static List<Integer> longestBouncyList(List<Integer> arr) {
//		if ((arr.size() < 2) || (arr.size() == 2 && !arr.get(0).equals(arr.get(1))))
//			return arr;
//		boolean state = arr.get(0) > arr.get(1);
//		int currentIndex = 0;
//		TreeMap<Integer, List<Integer>> lengths = new TreeMap<>();
//		while (currentIndex + 2 < arr.size()) {
//			List<Integer> list = new LinkedList<>();
//			do {
//				list.add(arr.get(currentIndex));
//				state = !state;
//			} while (currentIndex + 1 < arr.size() && !arr.get(currentIndex).equals(arr.get(++currentIndex)) && arr.get(currentIndex - 1) < arr.get(currentIndex) == state);
//			if (currentIndex + 1 < arr.size())
//				state = arr.get(currentIndex) < arr.get(currentIndex + 1);
//			if (!lengths.containsKey(list.size()))
//				lengths.put(list.size(), list);
//			if (list.size() > 1)
//				currentIndex--;
//			else
//				state = !state;
//		}
//		return lengths.lastEntry().getValue();
//	}

//	boolean cat(String[] yard, int minDistance) {
//		ArrayList<Integer[]> cats = new ArrayList<>();
//		for (int i = 0; i < yard.length; i++)
//			for (int j = 0; j < yard[0].length(); j++)
//				if (yard[i].charAt(j) != '-')
//					cats.add(new Integer[]{i, j});
//		if (cats.size() < 2)
//			return true;
//		if (Math.sqrt(Math.pow(cats.get(0)[0] - cats.get(1)[0], 2) + Math.pow(cats.get(0)[1] - cats.get(1)[1], 2)) < minDistance)
//			return false;
//		if (cats.size() == 2)
//			return true;
//		if (Math.sqrt(Math.pow(cats.get(0)[0] - cats.get(2)[0], 2) + Math.pow(cats.get(0)[1] - cats.get(2)[1], 2)) < minDistance)
//			return false;
//		return !(Math.sqrt(Math.pow(cats.get(1)[0] - cats.get(2)[0], 2) + Math.pow(cats.get(1)[1] - cats.get(2)[1], 2)) < minDistance);
//	}

	//		return Arrays.stream(Stream.of(s.replaceAll("[^a-z]","") // remove extra characters
//								   .split("")).sorted().collect(Collectors.joining()) // sort and group characters alphabetically
//								   .split("(?<=(.))(?!\\1)"))
//					 .filter(s1 -> s1.length() > 1) // remove strings shorter than 2 characters
//					 .sorted((o1, o2) -> o1.length() == o2.length() ? o1.compareTo(o2) : Integer.compare(o2.length(), o1.length())) // sort by length
//					 .collect(Collectors.joining())
//					 .split("")
//					 .filter()
//					 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
////					 .toArray(String[]::new);
	//				.entrySet().stream().filter(stringLongEntry -> stringLongEntry.getValue() > 1);

//	public static Map<String, Long> parse(String s) {
//		return Stream.of(s.split(""))
//				.filter(s1 -> s1.matches("[a-z]"))
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//	}
//
//	public static Map<String, Long> mix(String s1, String s2) {
//		return Stream.of(parse(s1), parse(s2))
//				.flatMap(map -> map.entrySet().stream())
//				.filter(stringLongEntry -> stringLongEntry.getValue() > 1)
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						Map.Entry::getValue,
//						Math::max));
//	}

//	public static Map<String, String[]> parse(String s, int i) {
//		return Stream.of(s.split(""))
//				.filter(s1 -> s1.matches("[a-z]"))
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//				.entrySet().stream()
//				.filter(stringLongEntry -> stringLongEntry.getValue() > 1)
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						stringLongEntry1 -> new String[] {String.valueOf(i), new String(new char[Math.toIntExact(stringLongEntry1.getValue())]).replace("\0", stringLongEntry1.getKey())}));
//	}

	public static Map<String, String[]> parse(String s, int i) {
		return Stream.of(s.split(""))
				.filter(s1 -> s1.matches("[a-z]"))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(stringLongEntry -> stringLongEntry.getValue() > 1)
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						stringLongEntry1 -> new String[] {String.valueOf(i), new String(new char[Math.toIntExact(stringLongEntry1.getValue())]).replace("\0", stringLongEntry1.getKey())}));
	}

	public static String mix(String s1, String s2) {

		var a = parse(s1, 1);
		var b = parse(s2, 2);

		var c = Stream.of(a, b)
				.flatMap(stringMap -> stringMap.entrySet().stream())
				.sorted(new Comparator<Map.Entry<String, String[]>>() {
					@Override
					public int compare(Map.Entry<String, String[]> o1, Map.Entry<String, String[]> o2) {
						int result = Integer.compare(o1.getValue()[1].length(), o2.getValue()[1].length());
						return result != 0 ? result : o1.getKey().compareTo(o2.getKey());
					}
				})
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						stringEntry -> new String[] {stringEntry.getValue()[0] + ":" + stringEntry.getValue()[1]},
						(strings, strings2) -> {
							int length = strings[0].length(), length2 = strings2[0].length();
							if (length > length2)
								return strings;
							if (length < length2)
								return strings2;
							return new String[] {"=:" + strings[0].substring(2)};
						}
				));

		System.out.println(c);

//		c.forEach((s, strings) -> System.out.println(s + " " + Arrays.toString(strings)));
		System.out.println("\n");

//		var c = Stream.of(a, b)
//				.flatMap(stringMap -> stringMap.entrySet().stream())
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						stringEntry -> new String[]{stringEntry.getValue()[0] + ":" + stringEntry.getValue()[1]}
//						, new BinaryOperator<String[]>() {
//							@Override
//							public String[] apply(String[] strings, String[] strings2) {
//								int length = strings[1].length(), length2 = strings2[1].length();
//								if (length > length2)
//									return "1:" + strings[0];
//								if (length < length2)
//									return "2:" + strings2[0];
//								return "=:" + strings[0];
//							}
//						}
//				));

//		a.forEach((s, strings) -> System.out.println(s + " " + Arrays.toString(strings)));
//		b.forEach((s, strings) -> System.out.println(s + " " + Arrays.toString(strings)));

		return s1 + "\n" + s2;
	}
//		var a = Stream.of(parse(s1, 1), parse(s2, 2));
//				.flatMap(map -> map.entrySet().stream())
//				.collect(Collectors.toMap(
//					Map.Entry::getKey,
//					Map.Entry::getValue,
//					Math::max));
//		Map<String, String> a = Stream.of(parse(s1, 1), parse(s2, 2))
//				.collect(Collectors.toMap(
//					Map.Entry::getKey,
//					Map.Entry::getValue,
//					Math::max));
//		return Stream.of(s1, s2)
//				.flatMap(s -> Stream.of(s.split(""))
//						.filter(ss -> ss.matches("[a-z]"))
//						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//						.entrySet().stream()
//						.filter(stringLongEntry -> stringLongEntry.getValue() > 1))
//				.collect(Collectors.toMap(
//					Map.Entry::getKey,
//					Map.Entry::getValue,
//					Math::max));

//				.collect(Collectors.toMap(
//					Map.Entry::getKey,
//					Map.Entry::getValue,
//					Math::max));
//				.collect(Collectors.toMap(
//						stringLongEntry1 -> stringLongEntry1.,
//						stringLongEntry1 -> new String(new char[Math.toIntExact(stringLongEntry1.getValue())]).replace("\0", stringLongEntry1.getKey()),
//						new BinaryOperator<Long>() {
//							@Override
//							public Long apply(Long a, Long b) {
//								System.out.println(a + " " + b);
//								return Math.max(a, b);
//							}
//						}));
//		return Stream.of(s1, s2)
//				.flatMap(s -> Stream.of(s.split(""))
//						.filter(ss -> ss.matches("[a-z]"))
//						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//						.entrySet().stream())
//				.filter(stringLongEntry -> stringLongEntry.getValue() > 1)
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						Map.Entry::getValue,
//						Math::max));
//	}

//		Map<String, Long> a = parse(s1), b = parse(s2);

//		var c = Stream.of(a, b)
//		var c = Stream.of(parse(s1), parse(s2))
//				.flatMap(map -> map.entrySet().stream())
//				.filter(stringLongEntry -> stringLongEntry.getValue() > 1)
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						Map.Entry::getValue,
//						Math::max));

//		HashMap<Integer, Integer> map1 = new HashMap<>();
//		map1.put(1,10);
//		map1.put(2,20);
//		map1.put(3,30);
//
//		HashMap<Integer, Integer> map2 = new HashMap<>();
//		map2.put(4,40);
//		map2.put(5,50);
//		map2.put(6,60);
//
//		map1.put(7, 70);
//		map2.put(7, 80);
//
//		var d = Stream.of(map1, map2)
//				.flatMap(map -> map.entrySet().stream())
//				.collect(Collectors.toMap(
//						Map.Entry::getKey,
//						Map.Entry::getValue,
//						Math::max));

//		Set<String> c = new HashSet<>(a.keySet());
//		c.addAll(b.keySet());
//
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);

//		System.out.println(d);

//		System.out.println(Arrays.toString(a.toArray()));
//		System.out.println(Arrays.toString(b.toArray()));

//		System.out.println();

//	public static double[] serve(double food, double flavour, int mouths) {
//		double[] doubles = new double[mouths];
//		for (int i = 0; i < mouths; i++)
//			if (flavour == 1)
//				doubles[i] = food / mouths;
//			else if (i == 0)
//				doubles[i] = food * (1 - flavour) / (1 - Math.pow(flavour, mouths));
//			else
//				doubles[i] = doubles[i - 1] * flavour;
//		return doubles;
//	}
//
//	public static boolean scramble(String str1, String str2) {
//		boolean possible = true;
//		for (char c : str2.toCharArray()) {
//			if (str1.indexOf(c) == -1)
//				possible = false;
//			str1.replaceFirst(String.valueOf(c), "");
//		}
//		return possible;
//	}

//	public static String[] getSocks(String name, String[] socks) {
//		HashSet<String> set = new HashSet<>();
//		for(String sock: socks) {
//			if(!set.add(sock) && "Henry".equals(name))
//				return new String[] {sock, sock};
//			if(set.size() == 2 && "Punky".equals(name))
//				return set.toArray(new String[2]);
//			set.toArray(new String[2]);
//		}
//		return new String[0];
//	}

	public static void main(String[] args) {

//		System.out.println(scramble("rkqodlw", "world"));

//		System.out.println((new CodeWars()).cat(new String[]{"-----------L", "--R---------", "------------", "------------", "------------", "--M---------"}, 2));

//		System.out.println((new CodeWars()).mix("Are they here", "yes, they are here"));
//		System.out.println(mix("aaaaaa rrr bbbb ccc dddd f zzzzzzzzzzz", "aaaaa bbbbb cc rrr eeee g"));


//		System.out.println(longestBouncyList(new LinkedList<>(Arrays.asList(7,9,6,10,5,11,10,12,13,4,2,5,1,6,4,8))));
//		System.out.println(longestBouncyList(new LinkedList<>(Arrays.asList(2, -1, 4, 13, 0, -1, -10, -8, -14, 4, -8, -4, 0))));
//		System.out.println(longestBouncyList(new LinkedList<>(Arrays.asList(-4, -15, -14, -4, -4, 2, -3, -2, 15, -15, -11, 13, -11))));
//		System.out.println(longestBouncyList(new LinkedList<>(Arrays.asList(7,7,7,7,7))));
//		System.out.println(longestBouncyList(new LinkedList<>(Arrays.asList(4, -4, 7, 13, 7, 5, -3, 7, 14, -13, -1, 2, -12))));
//		System.out.println(longestBouncyList(new LinkedList<>(Arrays.asList(-9, -10, -7, 4, -4, -5, 0, -5, 6, -1, 4, 1))));
//		System.out.println(longestBouncyList(new LinkedList<>(Arrays.asList(-7, 4, 18, 0, -9, 2, 10, 2, -7, -8, 8, 8))));
//		System.out.println(longestBouncyList(new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6))));

//		List<String> strings = Arrays.asList("A", "A", "A", "B", "B", "B", "C", "C", "E");
//		String[] strings1 = {"A", "A", "A", "B", "B"};
//		Map<Object, Long> freq = Stream.of(strings.toArray()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		var freq = Stream.of(strings.toArray()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//		int[] arr = {1,1,1,1,2,2,2,3,3,4};
//
////		Map<Integer, Long> freq = Stream.of(IntStream.of(arr).boxed().toArray(Integer[]::new)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
//		int a = Math.toIntExact(Stream.of(IntStream.of(arr).boxed().toArray(Integer[]::new)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getValue());
//
//		System.out.println(cats.get(1);

//		System.out.println(Collections.max(freq.values()));
//		System.out.println(freq.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey());
//		System.out.println(Stream.of(strings).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey());

//		System.out.println(function(strings));

//		System.out.println(n);
//		int sum = 0;
//		for (int i = 0; i <= n; i++) {
//			int temp = 0;
//			for (int j = 0; j <= n - i; j++) {
//				System.out.print(j + " ");
//				temp += j;
//			}
//			temp += 2 * i * (n - i + 1);
//			System.out.println(temp);
//			sum += temp;
//		}

//		int n = 5;
//
//		BigInteger sum = BigInteger.ZERO;
//		for (int i = 0; i <= n; i++) {
//			BigInteger temp = BigInteger.ZERO;
//			for (int j = 0; j <= n - i; j++)
//				temp = temp.add(BigInteger.valueOf(j));
//			temp = temp.add(BigInteger.valueOf(2 * i * (n - i + 1)));
//			sum = sum.add(temp);
//		}
//
//		System.out.println(sum);

//		long a = 9223372036854775807L, b = 9223372036854775807L;
//		try {
//			long c = a * b;
//			System.out.println(c);
//		} catch (ArithmeticException e) {
//			throw new ArithmeticException();
//		}



//		TreeSet<String> words = new TreeSet<>();
//		String[] a = new String[words.size()];
//		String[] w = words.toArray(cats.get(1);

//		BigInteger choose = BigInteger.ONE;
//		for (int i = 0; i < p; i++) {
//			choose = choose.multiply(BigInteger.valueOf(n - i)).divide(BigInteger.valueOf(i + 1));
//		}
//		return choose;

//		char[][] map = {{'O','O','N','O','N','O'},
//						{'O','O','O','O','O','O'},
//						{'X','O','O','O','O','O'},
//						{'O','O','O','O','O','O'},
//						{'O','O','O','O','O','O'}};
//
////		for (char[] row : map) {
////			System.out.println(Arrays.toString(row));
////			System.out.println();
////		}
//
//		//
//
//		ArrayList<int[]> shipMap = new ArrayList<>();
//		int xLoc = 0, yLoc = 0;
//
//		for (int i = 0; i < map.length; i++)
//			for (int j = 0; j < map[0].length; j++)
//				if (map[i][j] == 'X') {
//					yLoc = i;
//					xLoc = j;
//				}
//				else if (map[i][j] == 'N')
//					shipMap.add(new int[]{i, j, i == 0 ? 1 : -1});
//
////		System.out.println(xLoc + " " + yLoc + " " + shipMap + "\n");
//
//		for (int i = 0; i < map[0].length; i++) {
//			for (int j = (yLoc - 1) < 0 ? 0 : yLoc - 1; j <= (yLoc + 1 > map.length ? map.length : yLoc + 1); j++) {
////				for (int k = (i - 1) < 0 ? 0 : i - 1; k <= (i + 1 > map[0].length ? map[0].length : i + 1); k++) {
//				for (int k = i - 1 < 0 ? 0 : i - 1; k <= (i + 1 >= map[0].length ? map[0].length - 1 : i + 1); k++) {
//					System.out.print(map[j][k]);
//					if (map[j][k] == 'N')
//						System.out.println(false);
//				}
//				System.out.println();
//			}
//			System.out.println();
//
//			for (char[] chararr : map) {
//				for (char chars : chararr)
//					System.out.print(chars);
//				System.out.println();
//			}
//			System.out.println();
//
//			for (int[] ship : shipMap) {
//				if (ship[0] == map.length - 1)
//					ship[2] = -1;
//				else if (ship[0] == 0)
//					ship[2] = 1;
//				map[ship[0]][ship[1]] = 'O';
//				map[ship[0] += ship[2]][ship[1]] = 'N';
//			}
//
//			map[yLoc][i] = 'O';
//			if (i < map[0].length - 1)
//				map[yLoc][i + 1] = 'X';
//		}

	}
//        System.out.println(isInteresting(20934, new int[]{20934}));

//        String[] chars = {"A", "B", "C", "A", "C", "A"};
//        int[] ints = {1, 2, 3, 1, 3, 1};
//        System.out.println(Arrays.toString(ints));
//
//        Map<Integer, Long> freq = Stream.of(IntStream.of(ints).boxed().toArray(Integer[]::new)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        freq.forEach((integer, aLong) -> {
//            if (aLong % 2 != 0)
//                result = integer;
//        });
//        System.out.println(result);
//
//        System.out.println(System.getProperty("java.version"));
//        int num = 1234567;
//        int sum = 0;
//
////        int[]digits = Integer.toString(num).chars().map(c -> c-'0').toArray();
//        Arrays.asList(Integer.toString(num).split("")).forEach(new Consumer<>() {
//            int a = 0;
//            @Override
//            public void accept(String s) {
//                System.out.println(s + " " + cats.get(1);
//                a++;
//            }
//        });
//        int n = 1234567;
//
//        int sum;
//        int[] arr = {1, 10, 9, 12, 3, 4};
//        do {
//            sum = n;
//            int currentSum = 0;
//            for (int i = 0; n > 0; i = ++i % arr.length, n /= 10) {
//                currentSum += (n % 10) * arr[i];
//            }
//            System.out.println(currentSum);
//            n = currentSum;
//        } while (sum != n);
//        System.out.println(sum);

//        for(int d : digits)
//            System.out.print(d);

	//Map<Integer, Integer> freq = Stream.of(cats.get(1).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	// Collections.frequency(animals, "bat");
	//System.out.println(freq);
//    }
}