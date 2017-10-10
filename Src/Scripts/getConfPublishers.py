import mechanize
from bs4 import BeautifulSoup
import csv
import sys

def get_links(html):
	soup = BeautifulSoup(br.response().read(), "html5lib")

	tables = soup.find_all('table')
	tables = tables[2].find_all('table')
	links_conf_per_letter = tables[0].find_all('a')
	links = tables[1].find_all('a')

	return links, links_conf_per_letter

def get_publishers(file):
	f = open(file, 'r')
	reader = csv.reader(f)
	tmp_list = reader.next()
	f.close()
	return tmp_list

if len(sys.argv) != 2:
	print "usage: python", sys.argv[0], "publishers_source"
	exit()
	

br = mechanize.Browser()
br.set_handle_robots(False)
base_url = "http://wikicfp.com"
br.open("http://wikicfp.com/cfp/series?t=c&i=A")

links, links_conf_per_letter = get_links(br.response().read())

count = 0
publishers = get_publishers(sys.argv[1])
#print publishers

for link_letter in links_conf_per_letter:
	links = get_links(br.response().read())[0]
	for link in links:
		link = link['href'].replace(" ", "%20")
		br.follow_link(url=link)

		#Testando 
		soup = BeautifulSoup(br.response().read(), "html5lib")
		main_content = soup.find_all('table')[2].find_all('div')[0]
		#for child in main_content.children:
		#	print child
		#if "ICST" in main_content.contents:
		#	print "Esta no site"
		for cont in main_content.contents:
			print cont
		#print main_content.children[0]
		exit()
		#Testando


		print br.title()
		br.back()
		count += 1
	br.follow_link(url=link_letter['href'])

print count