import { AxiosPromise } from "axios";

import axios from "./axios";
import { FoodData, FoodDataCreate } from "../interfaces/food";

const ENDPOINT = "/foods";

export const getFoods = async (): AxiosPromise<FoodData[]> => {
    return axios.get(ENDPOINT);
}

export const createFood = (data: FoodDataCreate): AxiosPromise<FoodData> => {
    return axios.post(ENDPOINT, data)
}
