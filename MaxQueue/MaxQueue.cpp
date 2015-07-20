#include <iostream>
#include <stdio.h>
#include <deque>

using namespace std;

class MaxQueue {
  private:
    deque<int> maxq, data;
  public:
    void push_back(int element) {
      data.push_back(element);
      while (!maxq.empty() && maxq.back() < element) {
        maxq.pop_back();
      }
      maxq.push_back(element);
    }
    int pop_front() {
      if (data.empty()) return -1;
      if (maxq.front() == data.front()) maxq.pop_front();
      int ret = data.front();
      data.pop_front();
      return ret;
    }
    int get_max() {
      if (maxq.empty()) {
        return -1;
      }
      return maxq.front();
    }
};
int main() {
  MaxQueue q;
  int list[] = {2, 5, 10, 7, 11, 19};
  for (auto& i : list) {
    q.push_back(i);
    printf("%d %d\n", i, q.get_max());
  }
  for (auto& i : list) {
    q.pop_front();
  }
  for (auto& i : list) {
    q.push_back(i);
    printf("%d %d\n", i, q.get_max());
    q.pop_front();
  }
}
