package Quarter_2;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Testing {

	public static void main(String[] args) {
//		System.out.println(1);
//		class Thing {
//			Thing() {
//				System.out.println(2);
//			}
//		}
//		new Thing();

		int[] arr = {1,1,1,1,2,2,2,3,3,4};
//
////		Map<Integer, Long> freq = Stream.of(IntStream.of(arr).boxed().toArray(Integer[]::new)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
//		Optional<Map.Entry<Integer, Long>> a = Stream.of(IntStream.of(arr).boxed().toArray(Integer[]::new)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
//
//		System.out.println(a);


//		System.out.println(mostFrequentItemCount(arr));

//		List<String> strings = new ArrayList<>(Arrays.asList("A", "A", "A", "B", "B", "B", "C"));
//
//		var a =  strings.stream()
//						.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//						.entrySet()
//						.stream()
//						.max(Comparator.comparing(Map.Entry::getValue))
//						.filter(stringLongEntry -> stringLongEntry.getValue() > strings.size() / 2)
//						.map(Map.Entry::getKey).orElse("");
//
//		System.out.println(a);

	}

//	public static int mostFrequentItemCount(int[] collection) {
//		Optional<Map.Entry<Integer, Long>> a = Stream.of(IntStream.of(collection)
//				.boxed()
//				.toArray(Integer[]::new))
//				.collect(Collectors.groupingBy(identity(), Collectors.counting()))
//				.entrySet()
//				.stream()
//				.max((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
//		return a.map(integerLongEntry -> Math.toIntExact(integerLongEntry.getValue())).orElse(-1);

//		System.out.println(Stream.of(IntStream.of(collection).boxed().toArray(Integer[]::new)));
//		Stream.of(IntStream.of(collection).boxed().toArray(Integer[]::new)).forEach(System.out::print);
//		System.out.println();
//
////		System.out.println(Arrays.stream(collection).boxed().getClass());
//		Arrays.stream(collection).boxed().forEach(System.out::print);
//		System.out.println();

//		return Stream.of(IntStream.of(collection).boxed().toArray(Integer[]::new)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getValue).orElse((long) 0);
//		return Stream.of(IntStream.of(collection).boxed().toArray(Integer[]::new)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//				.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getValue).orElse((long) 0);

//		return Arrays.stream(collection).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//				.values().stream().mapToInt(Long::intValue).max().orElse( 0);

//	return 0;
//
//	}

}
