package com.xiao.algorithms.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BiggerIsBetter {

	public static String biggerIsGreater(String w) {
		/*
			Rough algo
			1. Starting from the second to the last char (from the right)
			2. Comparing everything to the right (starting from the end), if found a larger char, swap with it
			3. Sort everything (smallest to the largest) to the right of the index where the swapped happened
			4. stop once swap happens or continue after the end (leftmost) is encountered
		 */

		if (w == null || w.isEmpty()) {
			throw new IllegalArgumentException("input is empty");
		}
		if (w.length() == 1) {
			return "no answer";
		}

		char[] charArr = w.toCharArray();

		boolean foundSwap = false;

		int swappedIdx = -1;
		int arrLength = w.length();
		for (int i = arrLength - 2; i >= 0 && !foundSwap; i--) {
			for (int j = arrLength-1; j > i; j--) {
				char char1 = charArr[i];
				char char2 = charArr[j];
				if (char1 < char2) {
					charArr[j] = char1;
					charArr[i] = char2;
					swappedIdx = i;
					foundSwap = true;
					break;
				}
			}
		}

		if (swappedIdx != -1) {
			Arrays.sort(charArr, swappedIdx+1, charArr.length);
		}

		String result = new String(charArr);
		if (w.equals(result)) {
			return "no answer";
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(biggerIsGreater("dkhc"));
		System.out.println(biggerIsGreater("ab"));
		System.out.println(biggerIsGreater("aaaa"));
		System.out.println(biggerIsGreater("hefg"));
		System.out.println(biggerIsGreater("dhck"));
		System.out.println(biggerIsGreater("zyyyvvvvvuttsrqqokjjheedccb"));


		String input = "100\n" +
				"imllmmcslslkyoegymoa\n" +
				"fvincndjrurfh\n" +
				"rtglgzzqxnuflitnlyit\n" +
				"mhtvaqofxtyrz\n" +
				"zalqxykemvzzgaka\n" +
				"wjjulziszbqqdcpdnhdo\n" +
				"japjbvjlxzkgietkm\n" +
				"jqczvgqywydkunmjw\n" +
				"ehdegnmorgafrjxvksc\n" +
				"tydwixlwghlmqo\n" +
				"wddnwjneaxbwhwamr\n" +
				"pnimbesirfbivxl\n" +
				"mijamkzpiiniveik\n" +
				"qxtwpdpwexuej\n" +
				"qtcshorwyck\n" +
				"xoojiggdcyjrupr\n" +
				"vcjmvngcdyabcmjz\n" +
				"xildrrhpca\n" +
				"rrcntnbqchsfhvijh\n" +
				"kmotatmrabtcomu\n" +
				"bnfcejmyotvw\n" +
				"dnppdkpywiaxddoqx\n" +
				"tmowsxkrodmkkra\n" +
				"jfkaehlegohwggf\n" +
				"ttylsiegnttymtyx\n" +
				"kyetllczuyibdkwyihrq\n" +
				"xdhqbvlbtmmtshefjf\n" +
				"kpdpzzohihzwgdfzgb\n" +
				"kuywptftapaa\n" +
				"qfqpegznnyludrv\n" +
				"ufwogufbzaboaepslikq\n" +
				"jfejqapjvbdcxtkry\n" +
				"sypjbvatgidyxodd\n" +
				"wdpfyqjcpcn\n" +
				"baabpjckkytudr\n" +
				"uvwurzjyzbhcqmrypraq\n" +
				"kvtwtmqygksbim\n" +
				"ivsjycnooeodwpt\n" +
				"zqyxjnnitzawipqsm\n" +
				"blmrzavodtfzyepz\n" +
				"bmqlhqndacv\n" +
				"phvauobwkrcfwdecsd\n" +
				"vpygyqubqywkndhpzw\n" +
				"yikanhdrjxw\n" +
				"vnpblfxmvwkflqobrk\n" +
				"pserilwzxwyorldsxksl\n" +
				"qymbqaehnyzhfqpqprpl\n" +
				"fcakwzuqlzglnibqmkd\n" +
				"jkscckttaeifiksgkmxx\n" +
				"dkbllravwnhhfjjrce\n" +
				"imzsyrykfvjt\n" +
				"tvogoocldlukwfcajvix\n" +
				"cvnagtypozljpragvlj\n" +
				"hwcmacxvmus\n" +
				"rhrzcpprqccf\n" +
				"clppxvwtaktchqrdif\n" +
				"qwusnlldnolhq\n" +
				"yitveovrja\n" +
				"arciyxaxtvmfgquwb\n" +
				"pzbxvxdjuuvuv\n" +
				"nxfowilpdxwlpt\n" +
				"swzsaynxbytytqtq\n" +
				"qyrogefleeyt\n" +
				"iotjgthvslvmjpcchhuf\n" +
				"knfpyjtzfq\n" +
				"tmtbfayantmwk\n" +
				"asxwzygngwn\n" +
				"rmwiwrurubt\n" +
				"bhmpfwhgqfcqfldlhs\n" +
				"yhbidtewpgp\n" +
				"jwwbeuiklpodvzii\n" +
				"anjhprmkwibe\n" +
				"lpwhqaebmr\n" +
				"dunecynelymcpyonjq\n" +
				"hblfldireuivzekegit\n" +
				"uryygzpwifrricwvge\n" +
				"kzuhaysegaxtwqtvx\n" +
				"kvarmrbpoxxujhvgpw\n" +
				"hanhaggqzdpunkugzmhq\n" +
				"gnwqwsylqeuqr\n" +
				"qzkjbnyvclrkmdtc\n" +
				"argsnaqbquv\n" +
				"obbnlkoaklcx\n" +
				"ojiilqieycsasvqosycu\n" +
				"qhlgiwsmtxbffjsxt\n" +
				"vvrvnmndeogyp\n" +
				"ibeqzyeuvfzb\n" +
				"sajpyegttujxyx\n" +
				"zmdjphzogfldlkgbchnt\n" +
				"tbanvjmwirxx\n" +
				"gmdhdlmopzyvddeqyjja\n" +
				"yxvmvedubzcpd\n" +
				"soygdzhbckfuk\n" +
				"gkbekyrhcwc\n" +
				"wevzqpnqwtpfu\n" +
				"rbobquotbysufwqjeo\n" +
				"bpgqfwoyntuhkvwo\n" +
				"schtabphairewhfmp\n" +
				"rlmrahlisggguykeu\n" +
				"fjtfrmlqvsekq";

		BufferedReader bufferedReader = new BufferedReader(new StringReader(input));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

		int T = Integer.parseInt(bufferedReader.readLine().trim());

		IntStream.range(0, T).forEach(TItr -> {
			try {
				String w = bufferedReader.readLine();

				String result = biggerIsGreater(w);

				bufferedWriter.write(result);
				bufferedWriter.newLine();
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		bufferedReader.close();
		bufferedWriter.close();
	}
}
