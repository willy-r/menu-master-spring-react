export interface FoodData {
  id: number;
  title: string;
  image: string;
  price: number;
}

export interface FoodDataCreate {
  title: string;
  image: string;
  price: number;
}
