package de.test

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class Test : DescribeSpec() {

  init {

    describe("fail") {
      it("should fail") {
        true shouldBe false
      }
    }
  }
}
