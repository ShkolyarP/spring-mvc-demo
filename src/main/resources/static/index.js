angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductList = response.data;
            });
    };

    $scope.deleteProduct = function (id) {
        $http.get(contextPath + '/products/delete/' + id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changePrice = function (id, percent) {
        $http({
            url: contextPath + '/products/change_price',
            method: 'GET',
            params: {
                id: id,
                percent: percent
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.addProduct = function () {
        $http({
            url: contextPath + '/products',
            method: 'post',
            params: {
                title: $scope.newProduct.title,
                price: $scope.newProduct.price,

            }
        }).then(function (response) {
            $scope.newProduct = null;
            $scope.loadProducts();
        });
    }

    $scope.priceFilter = function () {
        $http({
            url: contextPath + '/products/find_by_price',
            method: 'GET',
            params: {
                min: $scope.p.min,
                max: $scope.p.max

            }
        }).then(function (response) {
            $scope.p = null;
            $scope.ProductList = response.data;
        });
    }

    $scope.loadProducts();
});