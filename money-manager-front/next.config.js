/** @type {import('next').NextConfig} */
const nextConfig = {
async headers() {
    return [
        {
        source: '/favicon.ico',
        headers: [
            {
            key: 'Content-Type',
            value: 'image/x-icon',
            },
        ],
        },
    ];
    },
};


module.exports = nextConfig
