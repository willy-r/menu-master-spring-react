import { AxiosPromise } from "axios";

import axios from "./axios";
import { FoodData } from "../interfaces/food";

const ENDPOINT = "/foods";

export const getFoods = async (): AxiosPromise<FoodData[]> => {
    return axios.get(ENDPOINT);
}
