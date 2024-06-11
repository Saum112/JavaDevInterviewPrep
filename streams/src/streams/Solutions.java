package streams;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solutions {

	private static Set<Transaction> collect2;

	public static void main(String[] args) {

		// Q1. Given a list of strings, we need to group them by their length using Java
		// Streams.

		List<String> strings = Arrays.asList("a", "bb", "ccc", "dd", "eee", "ffff", "ggggg");

		Map<Integer, List<String>> groupedByLength = strings.stream().collect(Collectors.groupingBy(String::length));

		groupedByLength.forEach((key, val) -> System.out.println(key + ":" + val));

		// Q2. Write a program to count the number of occurrences of each character in a
		// string using streams.

		String input = "countingcharacters";

		Map<Character, Long> collect = input.chars().mapToObj(x -> (char) x)
				.collect(Collectors.groupingBy(x -> x, Collectors.counting()));

		collect.forEach((key, val) -> System.out.println(key + " : " + val));

		// Q3. Given a list of Employee objects, find the employee with the highest
		// salary using streams.

		List<Employee> employees = Arrays.asList(new Employee("Alice", 60000), new Employee("Bob", 70000),
				new Employee("Charlie", 80000), new Employee("David", 75000));

		Optional<Employee> highestPaidEmployee = employees.stream()
				.max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));

		if (highestPaidEmployee.isPresent()) {
			System.out.println("Employee with the highest salary: " + highestPaidEmployee.get());
		} else {
			System.out.println("No employees found.");
		}

		// Q4. Using streams, filter a list of numbers to only include those greater
		// than 10 and then find their average.

		List<Integer> numbers = Arrays.asList(5, 15, 20, 25, 30, 3, 8, 10);
		Double average = numbers.stream().filter(n -> n > 10).mapToInt(n -> n).average().orElse(0);

		System.out.println("average : " + average);

		// Q5. Using Java Streams, convert a list of strings to a map where the key is
		// the string and the value is its length.
		List<String> data = List.of("apple", "banana", "cherry", "date");
		Map<String, Integer> stringLengthMap = strings.stream().collect(Collectors.toMap(x -> x, x -> x.length()));

		// Print the map
		stringLengthMap.forEach((key, value) -> System.out.println(key + ": " + value));

		// Q6. Write a stream operation to flatten a list of lists of integers into a
		// single list of integers.
		List<List<Integer>> listOfLists = List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));
		List<Integer> flattenedList = listOfLists.stream().flatMap(list -> list.stream()).collect(Collectors.toList());

		flattenedList.forEach(ele -> System.out.println(ele));

		// Q7. Given a list of transactions, filter out transactions of a specific type
		// and collect them into a set.

		List<Transaction> transactions = List.of(new Transaction("deposit", 100.0), new Transaction("withdrawal", 50.0),
				new Transaction("deposit", 200.0), new Transaction("withdrawal", 30.0),
				new Transaction("transfer", 75.0));

		Set<Transaction> filteredTransactions = transactions.stream().filter(x -> x.getType().equals("deposit"))
				.collect(Collectors.toSet());
		filteredTransactions.forEach(ele -> System.out.println(ele));

		// Q8. Create a stream pipeline to find the first name of the oldest person in a
		// list of Person objects.
		List<Person> people = Arrays.asList(new Person("John", "Doe", 30), new Person("Jane", "Smith", 40),
				new Person("Alice", "Johnson", 50), new Person("Bob", "Brown", 60));

		Optional<Person> maxAge = people.stream().max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
		if (maxAge.isPresent()) {

			System.out.println(maxAge.get().getFirstName());
		}

		// Q9. Write a program to find the first non-repeating character in a string
		// using streams.

		String inputData = "ilikecodingandsinging";

		// Create a map to count the occurrences of each character
		LinkedHashMap<Character, Long> frequencyMap = inputData.chars().mapToObj(x -> (char) x)
				.collect(Collectors.groupingBy(x -> x, LinkedHashMap::new, Collectors.counting()));

		// Find the first non-repeating character
		Character findFirst = frequencyMap.entrySet().stream().filter(entry -> entry.getValue() == 1)
				.map(entry -> entry.getKey()).findFirst().orElse(null);

		System.out.println(findFirst);

		// Q10. Using streams, find the sum of the squares of a list of integers.

		List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		int sum = numberList.stream().mapToInt(x -> x * x).sum();
		System.out.println(sum);

		// Q11. Write a stream operation to skip the first 5 elements in a list and then
		// print the rest.

		numberList.stream().skip(5).forEach(x -> System.out.print(x + " "));

		// Q12. Create a stream to generate an infinite sequence of random numbers and
		// print the first 10.
		Stream.generate(() -> new Random().nextInt()).limit(10).forEach(n -> System.out.print(n + " "));

		// Q13. Using streams, partition a list of integers into even and odd numbers.
		Map<Boolean, List<Integer>> partitionList = numberList.stream()
				.collect(Collectors.partitioningBy(ele -> ele % 2 == 0));
		List<Integer> evenNumbers = partitionList.get(true);
		List<Integer> oddNumbers = partitionList.get(false);

		System.out.println("Even numbers: " + evenNumbers);
		System.out.println("Odd numbers: " + oddNumbers);

		// Q14. Write a program to convert a list of strings to a list of their
		// respective lengths using streams.

		List<String> info = Arrays.asList("apple", "banana", "orange", "grape", "kiwi");
		List<Integer> lengths = info.stream().map(ele -> ele.length()).collect(Collectors.toList());
		System.out.println("Lengths of strings: " + lengths);

		// Q15. Using streams, find the product of all elements in a list of integers.
		Integer product = numberList.stream().reduce(1, (x, y) -> x * y);
		System.out.println(product);

		// Q16. Create a stream pipeline to collect all unique words from a list of
		// sentences.
		List<String> sentences = Arrays.asList("The quick brown fox", "jumps over the lazy dog", "and the lazy cat");
		sentences.stream().flatMap(list -> Arrays.stream(list.split("\\s+"))).distinct().collect(Collectors.toList());

		// Q17. Write a program to filter out null values from a list of strings using
		// streams.
		List<String> nullStrings = Arrays.asList("apple", null, "banana", "orange", null, "grape", "kiwi");
		List<String> removeNULLString = nullStrings.stream().filter(ele -> ele != null).collect(Collectors.toList());
		System.out.println(removeNULLString);

		// Q18. Using streams, merge two lists of integers and remove duplicates.
		List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

		// Merge the two lists and remove duplicates
		List<Integer> mergedList = Stream.concat(list1.stream(), list2.stream()).distinct()
				.collect(Collectors.toList());
		System.out.println(mergedList);

		// Q19. Write a stream operation to check if any string in a list starts with a
		// specific prefix.
		boolean anyMatch = info.stream().anyMatch(ele -> ele.startsWith("ap"));
		System.out.println(anyMatch);

		// Q20. Write a program to concatenate a list of strings into a single string,
		// separated by commas, using streams.

		String mergedString = info.stream().collect(Collectors.joining(","));
		System.out.println(mergedString);

	}
}
