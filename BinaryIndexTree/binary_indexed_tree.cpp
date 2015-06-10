// index uses [1 .. n]

int bit[MAX_N + 1], n;

int sum(int i) {
	int s = 0;
	while (i > 0) {
		s += bit[i];
		i -= i & -i;
		// index can also appear as "i = i & (i - 1);"
	}
	return s;
}

void add(int i, int x) {
	while (i <= n) {
		bit[i] += x;
		i += i & -i;
	}
}